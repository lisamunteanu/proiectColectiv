package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EpisodesRepository extends JpaRepository<Episode,Integer> {
    @Query(value = "select * from episodes e where e.id_season= :seasonId",nativeQuery = true)
    List<Episode> getAllEpisodeFromASeason(@Param("seasonId") String seasonId);

    @Query(value = "select MAX(number) from episodes e where e.id_season= :seasonId",nativeQuery = true)
    Optional<Integer> getLasEpisodeNumber(@Param("seasonId") Integer seasonId);
}
