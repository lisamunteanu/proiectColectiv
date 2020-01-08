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

}
