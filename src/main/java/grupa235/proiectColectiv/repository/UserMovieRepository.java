package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.identities.UserMovieId;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.model.UserMovies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMovieRepository extends JpaRepository<UserMovies, UserMovieId> {
    @Query(value = "select * from user_movie e where e.id_user= :userId and e.watch_later=true", nativeQuery = true)
    List<UserMovies> getAllWatchLaterMoviesByUser(@Param("userId") RepoUser user);

    @Query(value = "select * from user_movie e where e.id_user= :userId and e.history=true", nativeQuery = true)
    List<UserMovies> getAllHistoryMoviesByUser(@Param("userId") RepoUser user);

}
