package grupa235.proiectColectiv.repository;

import grupa235.proiectColectiv.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends JpaRepository<Series,Integer> {
}
