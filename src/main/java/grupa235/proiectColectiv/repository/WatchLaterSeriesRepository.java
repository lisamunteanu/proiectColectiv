package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.identities.WatchLaterSeriesId;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.model.Series;
import grupa235.proiectColectiv.model.WatchLaterSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchLaterSeriesRepository extends JpaRepository<WatchLaterSeries, WatchLaterSeriesId> {
    @Query(value = "select e.WatchLaterSeriesId.idSeries from series_watch_later e where e.watchLaterSeriesId.idUser= :userId", nativeQuery = true)
    List<Series> getAllSeriesByUser(@Param("userId") RepoUser user);
}
