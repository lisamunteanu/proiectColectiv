package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendsRepository extends JpaRepository<Friends,Long> {

    @Query(value = "select * from friends f where f.first_user = :userId or f.second_user = :userId",nativeQuery = true)
    Optional<List<Friends>> getFriends(@Param("userId") Long userId);

    @Query(value = "select * from friends f where f.first_user = :firstId and f.second_user = :secondId limit 1",nativeQuery = true)
    Optional<Friends> existFriends(@Param("firstId") Long firstId, @Param("secondId") Long secondId);
}
