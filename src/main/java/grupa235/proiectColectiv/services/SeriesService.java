package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.frontendModel.SerialDetails;
import grupa235.proiectColectiv.frontendModel.SerialModel;
import grupa235.proiectColectiv.model.Series;

import java.util.List;

public interface SeriesService {
    List<Series> getAllSeries();

    Series addSerial(SerialModel serialModel);

    Series findSerialById(int id) throws Exception;

    Series updateSerial(Series serial) throws Exception;

    List<Series> findAllWatchLaterSeriesForUser(String username);

    void addWatchLaterSeries(String username, Integer seriesId);

    void deleteWatchLaterSeries(String username, Integer seriesId);

    Boolean watchLaterSeries(String username, Integer movieId);

    SerialDetails getDetailsForASerial(String serialName);
}
