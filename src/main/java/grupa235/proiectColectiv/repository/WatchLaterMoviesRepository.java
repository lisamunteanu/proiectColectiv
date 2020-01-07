//package grupa235.proiectColectiv.repository;
//
//import grupa235.proiectColectiv.identities.WatchLaterMovieId;
//import grupa235.proiectColectiv.model.Movie;
//import grupa235.proiectColectiv.model.RepoUser;
//import grupa235.proiectColectiv.model.WatchLaterMovies;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface WatchLaterMoviesRepository extends JpaRepository<WatchLaterMovies, WatchLaterMovieId> {
//    @Query(value = "select e.watchLaterMovieId.idMovie from movie_watch_later e where e.watchLaterMovieId.idUser= :userId", nativeQuery = true)
//    List<Movie> getAllMoviesByUser(@Param("userId") RepoUser user);
//}
