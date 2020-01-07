//package grupa235.proiectColectiv.model;
//
//
//import grupa235.proiectColectiv.identities.WatchLaterSeriesId;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name="series_watch_later")
//public class WatchLaterSeries {
//    @EmbeddedId
//    private WatchLaterSeriesId watchLaterSeriesId;
//
//    @Column(name="addedDate")
//    private LocalDateTime addedDate;
//
//    public WatchLaterSeries(WatchLaterSeriesId watchLaterSeriesId, LocalDateTime addedDate) {
//        this.watchLaterSeriesId = watchLaterSeriesId;
//        this.addedDate = addedDate;
//    }
//
//    public WatchLaterSeriesId getWatchLaterSeriesId() {
//        return watchLaterSeriesId;
//    }
//
//    public void setWatchLaterSeriesId(WatchLaterSeriesId watchLaterSeriesId) {
//        this.watchLaterSeriesId = watchLaterSeriesId;
//    }
//
//    public LocalDateTime getAddedDate() {
//        return addedDate;
//    }
//
//    public void setAddedDate(LocalDateTime addedDate) {
//        this.addedDate = addedDate;
//    }
//}
