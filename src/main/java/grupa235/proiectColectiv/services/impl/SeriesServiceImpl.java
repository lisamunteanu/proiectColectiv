package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.converter.ConvertData;
import grupa235.proiectColectiv.frontendModel.SerialModel;
import grupa235.proiectColectiv.model.Series;
import grupa235.proiectColectiv.repository.SeriesRepository;
import grupa235.proiectColectiv.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public  class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;

    @Autowired
    public SeriesServiceImpl(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public List<Series> getAllSeries() {
        return this.seriesRepository.findAll();
    }

    public Series addSerial(SerialModel serialModel) {
        Series serial = ConvertData.convertSerialModelToSerial(serialModel);
        this.seriesRepository.save(serial);
        return serial;
    }

    public Series findSerialById(int id) throws Exception {
        return this.seriesRepository.findById(id).get();
    }

    public Series updateSerial(Series serial) throws Exception {
        Series isPresentSerial = this.seriesRepository.findById(serial.getId()).get();
        if (isPresentSerial != null) {
            isPresentSerial.setGenres(serial.getGenres());
            isPresentSerial.setDirector(serial.getDirector());
            isPresentSerial.setName(serial.getName());
            isPresentSerial.setNoOfSeasons(serial.getNoOfSeasons());
            isPresentSerial.setNoOfEpisodes(serial.getNoOfEpisodes());
            this.seriesRepository.save(isPresentSerial);
        }
        return isPresentSerial;
    }

    @Override
    public List<Series> FilterSeriesByGenres(@PathVariable String genre) {
        List<Series> allSeries = seriesRepository.findAll();
        for (Series s : allSeries) {
            if(s.getGenres().contains(genre)) {
                allSeries.add(s);
            }
        }
        return allSeries;
    }

    @Override
    public List<Series> SortSeriesByName() {
        List<Series> allSeries = seriesRepository.findAll();
        Comparator<Series> compareByName = Comparator.comparing(Series::getName);
        Collections.sort(allSeries, compareByName);
        return allSeries;
    }



    @Override
    public List<Series> SortSeriesByRating() {
        List<Series> allSeries = seriesRepository.findAll();
        Comparator<Series> compareByRating = Comparator.comparing(Series::getRating);
        Collections.sort(allSeries, compareByRating);
        return allSeries;
    }

    @Override
    public List<Series> SortSeriesByStartYear() {
        List<Series> allSeries = seriesRepository.findAll();
        Comparator<Series> compareByStartYear = Comparator.comparing(Series::getStartYear);
        Collections.sort(allSeries, compareByStartYear);
        return allSeries;
    }
}
