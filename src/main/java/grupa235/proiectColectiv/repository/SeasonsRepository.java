package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeasonsRepository extends JpaRepository<Season,Integer> {

    @Query(value = "select * from seasons s where s.id_series= :serialId",nativeQuery = true)
    List<Season> getAllSeasonsFromASerial(@Param("serialId") String serialId);

    @Query(value = "select MAX(number) from seasons s where s.id_series= :serialId",nativeQuery = true)
    Optional<Integer> getLastSeasonNumber(@Param("serialId") Integer serialId);

    @Query(value = "select id from seasons s where s.id_series= :serialId ORDER BY id DESC LIMIT 1",nativeQuery = true)
    Optional<Integer> getLastSeasonId(@Param("serialId") Integer serialId);

}
