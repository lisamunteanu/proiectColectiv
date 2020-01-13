package grupa235.proiectColectiv.model;

import grupa235.proiectColectiv.identities.UserMovieId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="user_movie")
public class UserMovies implements Serializable {

    @EmbeddedId
    private UserMovieId userMovieId;

    @Column(name="addedDateToWatchLater")
    private LocalDateTime addedDate;

    @Column(name="watchLater")
    private Boolean watchLater;

    @Column(name="watchedDateHistory")
    private LocalDateTime watchedDate;

    @Column(name="history")
    private Boolean history;

    @Column(name="rating")
    private Integer rating;

    public UserMovies() {
    }

    public UserMovies(UserMovieId userMovieId, LocalDateTime addedDate, Boolean watchLater, LocalDateTime watchedDate, Boolean history, Integer rating) {
        this.userMovieId = userMovieId;
        this.addedDate = addedDate;
        this.watchLater = watchLater;
        this.watchedDate = watchedDate;
        this.history = history;
        this.rating = rating;
    }

    public UserMovieId getUserMovieId() {
        return userMovieId;
    }

    public void setUserMovieId(UserMovieId userMovieId) {
        this.userMovieId = userMovieId;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public Boolean getWatchLater() {
        return watchLater;
    }

    public void setWatchLater(Boolean watchLater) {
        this.watchLater = watchLater;
    }

    public LocalDateTime getWatchedDate() {
        return watchedDate;
    }

    public void setWatchedDate(LocalDateTime watchedDate) {
        this.watchedDate = watchedDate;
    }

    public Boolean getHistory() {
        return history;
    }

    public void setHistory(Boolean history) {
        this.history = history;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
