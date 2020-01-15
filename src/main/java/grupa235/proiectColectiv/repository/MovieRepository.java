package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    @Query(value ="select * from movies m where m.name = :movieName limit 1" ,nativeQuery = true)
    Optional<Movie> getMovieByName(@Param("movieName") String movieName);

    @Query(value = "select * from movies m order by addedDate desc",nativeQuery = true)
    List<Movie> SortMoviesByAddedDate();

    @Query(value = "select * from movies m order by rating desc",nativeQuery = true)
    List<Movie> SortMoviesByRating();

    @Query(value = "SELECT * FROM movies m WHERE FIND_IN_SET(:genre,m.genres)",nativeQuery = true)
    List<Movie> FilterMoviesByGenres(@Param("genre") String genre);

    @Query(value = "select * from movies m order by releaseYear desc",nativeQuery = true)
    List<Movie> SortMoviesByReleaseYear();

    @Query(value = "select * from bingewatch.movies m order by name",nativeQuery = true)
    List<Movie> SortMoviesByName();

}
