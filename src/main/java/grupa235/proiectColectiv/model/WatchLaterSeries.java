package grupa235.proiectColectiv.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="series_watch_later")
public class WatchLaterSeries {
    @EmbeddedId
    @ManyToOne
    @Column(name="idUser",updatable = false, nullable = false)
    private RepoUser user;

    @EmbeddedId
    @ManyToOne
    @Column(name = "idSeries", updatable = false, nullable = false)
    private Series series;

    @Column(name="addedDate")
    private LocalDateTime addedDate;

    public WatchLaterSeries(RepoUser user, Series series, LocalDateTime addedDate) {
        this.user = user;
        this.series = series;
        this.addedDate = addedDate;
    }

    public RepoUser getUser() {
        return user;
    }

    public void setUser(RepoUser user) {
        this.user = user;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }
}
