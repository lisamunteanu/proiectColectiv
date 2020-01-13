package grupa235.proiectColectiv.identities;

import grupa235.proiectColectiv.model.Movie;
import grupa235.proiectColectiv.model.RepoUser;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserMovieId implements Serializable {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idUser",updatable = false, nullable = false)
    private RepoUser user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idMovie", updatable = false, nullable = false)
    private Movie movie;

    public UserMovieId(RepoUser user, Movie movie) {
        this.user = user;
        this.movie = movie;
    }
    public UserMovieId() {
    }

    public RepoUser getUser() {
        return user;
    }

    public void setUser(RepoUser user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMovieId that = (UserMovieId) o;
        return user==that.user &&
                movie==that.movie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, movie);
    }
}
