package grupa235.proiectColectiv.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="movie_history")
public class MovieHistory implements Serializable {
    @EmbeddedId
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser")
    private RepoUser user;

    @EmbeddedId
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idMovie")
    private Movie movie;

    @Column(name="watchingDate")
    private LocalDateTime watchingDate;

    @Column(name="rating")
    private Integer rating;


    public MovieHistory(RepoUser user, Movie movie, LocalDateTime watchingDate) {
        this.user = user;
        this.movie = movie;
        this.watchingDate = watchingDate;
    }

    public MovieHistory() {
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

    public LocalDateTime getWatchingDate() {
        return watchingDate;
    }

    public void setWatchingDate(LocalDateTime watchingDate) {
        this.watchingDate = watchingDate;
    }
}
