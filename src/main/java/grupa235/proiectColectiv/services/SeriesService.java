package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.frontendModel.SerialDetails;
import grupa235.proiectColectiv.frontendModel.SerialModel;
import grupa235.proiectColectiv.model.Series;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface SeriesService {
    List<Series> getAllSeries();

    Series addSerial(SerialModel serialModel);

    Series findSerialById(int id) throws Exception;

    Series updateSerial(Series serial) throws Exception;

    SerialDetails getDetailsForASerial(String serialName);

    List<Series> FilterSeriesByGenres(String genre);

    List<Series>  SortSeriesByName();

    List<Series> SortSeriesByRating();

    List<Series> SortSeriesByStartYear();

   List<Series> SortSeriesEndYear();


}
