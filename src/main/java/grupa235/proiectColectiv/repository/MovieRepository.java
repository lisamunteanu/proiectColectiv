package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    @Query(value ="select * from movies m where m.name = :movieName limit 1" ,nativeQuery = true)
    Optional<Movie> getMovieByName(@Param("movieName") String movieName);
}
