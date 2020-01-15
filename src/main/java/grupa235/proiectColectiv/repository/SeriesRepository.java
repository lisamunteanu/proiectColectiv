package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeriesRepository extends JpaRepository<Series,Integer> {
    @Query(value ="select * from series s where s.name= :serialName" ,nativeQuery = true)
    Optional<Series> getSerialByName(@Param("serialName") String serialName);

    @Query(value = "select * from series s order by name",nativeQuery = true)
    List<Series> SortSeriesByName();

    @Query(value = "select * from series s order by rating",nativeQuery = true)
    List<Series> SortSeriesByRating();

    @Query(value = "select * from series s order by start_year desc",nativeQuery = true)
    List<Series> SortSeriesByStartYear();

    @Query(value = "select * from series s order by end_year desc",nativeQuery = true)
    List<Series> SortSeriesEndYear();

    @Query(value = "SELECT * FROM series s WHERE FIND_IN_SET(:genre,s.genres)",nativeQuery = true)
    List<Series> FilterSeriesByGenres(@Param("genre") String genre);

}

