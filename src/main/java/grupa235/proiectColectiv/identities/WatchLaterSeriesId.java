package grupa235.proiectColectiv.identities;

import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.model.Series;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WatchLaterSeriesId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idUser",updatable = false, nullable = false)
    private RepoUser user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSeries", updatable = false, nullable = false)
    private Series series;

    public WatchLaterSeriesId(RepoUser user, Series series) {
        this.user = user;
        this.series = series;
    }

    public RepoUser getUser() {
        return user;
    }

    public void setUser(RepoUser user) {
        this.user = user;
    }

    public Series getMovie() {
        return series;
    }

    public void setMovie(Series movie) {
        this.series = movie;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchLaterSeriesId that = (WatchLaterSeriesId) o;
        return user==that.user &&
                series==that.series;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, series);
    }
}
