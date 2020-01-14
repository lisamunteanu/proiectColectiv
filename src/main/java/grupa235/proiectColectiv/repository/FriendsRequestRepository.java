package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.model.FriendsRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendsRequestRepository extends JpaRepository<FriendsRequest,Long> {

    @Query(value ="select * from friendsrequest fr where fr.sendby = :userSendBy and fr.id_user = :userId limit 1" ,nativeQuery = true)
    Optional<FriendsRequest> existThisRequest(@Param("userSendBy") String userName, @Param("userId") Long userId);

    @Modifying
    @Query(value ="delete from friendsrequest where sendby = :userSendBy and id_user = :userId " ,nativeQuery = true)
    void deleteRequest(@Param("userSendBy") String userSendBy, @Param("userId") Long userId);

}
