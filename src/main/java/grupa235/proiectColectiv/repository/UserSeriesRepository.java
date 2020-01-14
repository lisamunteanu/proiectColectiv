package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.identities.UserSeriesId;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.model.UserSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSeriesRepository extends JpaRepository<UserSeries, UserSeriesId> {
    @Query(value = "select * from user_series e where e.id_user= :userId and e.history=true", nativeQuery = true)
    List<UserSeries> getAllHistorySeriesByUser(@Param("userId") RepoUser user);

    @Query(value = "select * from user_series e where e.id_user= :userId and e.watch_later=true", nativeQuery = true)
    List<UserSeries> getAllWatchLaterSeriesByUser(@Param("userId") RepoUser user);
}
