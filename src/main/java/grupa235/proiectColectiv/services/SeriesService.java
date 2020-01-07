package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.frontendModel.SerialModel;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.model.Series;

import java.util.List;

public interface SeriesService {
    List<Series> getAllSeries();

    Series addSerial(SerialModel serialModel);

    Series findSerialById(int id) throws Exception;

    Series updateSerial(Series serial) throws Exception;

    List<Series> findAllWatchLaterSeriesForUser(RepoUser user);

    void addWatchLaterSeries(RepoUser user, Integer seriesId);

    void deleteWatchLaterSeries(RepoUser user, Integer seriesId);
}
