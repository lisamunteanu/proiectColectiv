package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.identities.MovieHistoryId;
import grupa235.proiectColectiv.model.MovieHistory;
import grupa235.proiectColectiv.model.RepoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieHistoryRepository extends JpaRepository<MovieHistory, MovieHistoryId> {
    @Query(value = "select * from movie_history e where  e.id_user= :userId", nativeQuery = true)
    List<MovieHistory> getAllMoviesByUser(@Param("userId")RepoUser user);
}
