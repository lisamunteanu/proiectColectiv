package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeriesRepository extends JpaRepository<Series,Integer> {
    @Query(value ="select * from series s where s.name= :serialName" ,nativeQuery = true)
    Optional<Series> getSerialByName(@Param("serialName") String serialName);

    @Query(value = "select count(e.id) from series as se inner join seasons as s on s.id_series=se.id inner join episodes as e on s.id=e.id_season where se.id= :id", nativeQuery = true)
    Integer getSeriesNumberOfEpisodes(@Param("id") Integer id);

    @Query(value = "select count(s.id) from series as se inner join seasons as s on s.id_series=se.id where se.id= :id", nativeQuery = true)
    Integer getSeriesNumberOfSeasons(@Param("id") Integer id);
}
