package grupa235.proiectColectiv.model;

import grupa235.proiectColectiv.identities.MovieHistoryId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="movie_history")
public class MovieHistory implements Serializable {
    @EmbeddedId
    private MovieHistoryId movieHistoryId;

    @Column(name="watchingDate")
    private LocalDateTime watchingDate;

    @Column(name="rating")
    private Integer rating;

    public MovieHistory() {
    }

    public MovieHistory(MovieHistoryId movieHistoryId, LocalDateTime watchingDate, Integer rating) {
        this.movieHistoryId = movieHistoryId;
        this.watchingDate = watchingDate;
        this.rating = rating;
    }

    public MovieHistoryId getMovieHistoryId() {
        return movieHistoryId;
    }

    public void setMovieHistoryId(MovieHistoryId movieHistoryId) {
        this.movieHistoryId = movieHistoryId;
    }

    public LocalDateTime getWatchingDate() {
        return watchingDate;
    }

    public void setWatchingDate(LocalDateTime watchingDate) {
        this.watchingDate = watchingDate;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
