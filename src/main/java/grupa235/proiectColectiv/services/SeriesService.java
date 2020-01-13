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

    SerialDetails getDetailsForASerial(String serialName);
}
