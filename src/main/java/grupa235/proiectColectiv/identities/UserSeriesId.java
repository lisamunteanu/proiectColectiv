package grupa235.proiectColectiv.identities;

import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.model.Series;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserSeriesId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idUser",updatable = false, nullable = false)
    private RepoUser user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSeries", updatable = false, nullable = false)
    private Series series;

    public UserSeriesId(RepoUser user, Series series) {
        this.user = user;
        this.series = series;
    }

    public UserSeriesId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSeriesId that = (UserSeriesId) o;
        return user==that.user &&
                series==that.series;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, series);
    }
}
