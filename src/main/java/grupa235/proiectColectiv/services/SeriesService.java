package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.frontendModel.SerialDetails;
import grupa235.proiectColectiv.frontendModel.SerialModel;
import grupa235.proiectColectiv.frontendModel.SeriesHistory;
import grupa235.proiectColectiv.model.Series;

import java.util.List;

public interface SeriesService {
    List<SerialModel> getAllSeries();

    Series addSerial(SerialModel serialModel);

    SerialModel findSerialById(int id) throws Exception;

    Series updateSerial(Series serial) throws Exception;

    List<Series> findAllWatchLaterSeriesForUser(String username);

    Boolean watchLaterSeries(String username, Integer movieId);

    SerialDetails getDetailsForASerial(String serialName);

    Boolean setSeriesRating(String username, Integer seriesId, Integer rating);

    List<SeriesHistory> findAllHistorySeriesForUser(String username);

    Boolean historySeries(String username, Integer seriesId, Integer episodeId);
}
