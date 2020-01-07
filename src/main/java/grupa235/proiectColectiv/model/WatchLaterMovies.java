package grupa235.proiectColectiv.model;

import grupa235.proiectColectiv.identities.WatchLaterMovieId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="movie_watch_later")
public class WatchLaterMovies implements Serializable {

    @EmbeddedId
    private WatchLaterMovieId watchLaterMovieId;

    @Column(name="addedDate")
    private LocalDateTime addedDate;

    public WatchLaterMovies(WatchLaterMovieId watchLaterMovieId, LocalDateTime addedDate) {
        this.watchLaterMovieId = watchLaterMovieId;
        this.addedDate = addedDate;
    }
    public WatchLaterMovies(){
    }

    public WatchLaterMovieId getWatchLaterMovieId() {
        return watchLaterMovieId;
    }

    public void setWatchLaterMovieId(WatchLaterMovieId watchLaterMovieId) {
        this.watchLaterMovieId = watchLaterMovieId;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }
}
