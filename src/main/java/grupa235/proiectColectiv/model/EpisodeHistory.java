package grupa235.proiectColectiv.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="episode_history")
public class EpisodeHistory implements Serializable {
    @EmbeddedId
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser")
    private RepoUser user;

    @EmbeddedId
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEpisode")
    private Episode episode;

    @Column(name="watchingDate")
    private LocalDateTime watchingDate;

    @Column(name="rating")
    private Integer rating;

    public EpisodeHistory(RepoUser user, Episode episode, LocalDateTime watchingDate, Integer rating) {
        this.user = user;
        this.episode = episode;
        this.watchingDate = watchingDate;
        this.rating = rating;
    }

    public EpisodeHistory() {
    }

    public RepoUser getUser() {
        return user;
    }

    public void setUser(RepoUser user) {
        this.user = user;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
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
