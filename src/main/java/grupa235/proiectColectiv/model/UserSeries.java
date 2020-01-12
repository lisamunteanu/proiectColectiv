package grupa235.proiectColectiv.model;


import grupa235.proiectColectiv.identities.UserSeriesId;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="user_series")
public class UserSeries {
    @EmbeddedId
    private UserSeriesId userSeriesId;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "idEpisode")
    private Episode episode;

    public UserSeries() {
    }

    public UserSeries(UserSeriesId userSeriesId, LocalDateTime addedDate, Boolean watchLater, LocalDateTime watchedDate, Boolean history, Integer rating, Episode episode) {
        this.userSeriesId = userSeriesId;
        this.addedDate = addedDate;
        this.watchLater = watchLater;
        this.watchedDate = watchedDate;
        this.history = history;
        this.rating = rating;
        this.episode = episode;
    }

    public UserSeriesId getUserSeriesId() {
        return userSeriesId;
    }

    public void setUserSeriesId(UserSeriesId userSeriesId) {
        this.userSeriesId = userSeriesId;
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

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
}
