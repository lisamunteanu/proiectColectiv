package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.converter.ConvertData;
import grupa235.proiectColectiv.frontendModel.SerialDetails;
import grupa235.proiectColectiv.frontendModel.SerialModel;
import grupa235.proiectColectiv.frontendModel.SeriesHistory;
import grupa235.proiectColectiv.identities.UserSeriesId;
import grupa235.proiectColectiv.model.Episode;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.model.Series;
import grupa235.proiectColectiv.model.UserSeries;
import grupa235.proiectColectiv.repository.*;
import grupa235.proiectColectiv.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeriesServiceImpl implements SeriesService {

    private final UserSeriesRepository userSeriesRepository;

    private final SeriesRepository seriesRepository;
    private final SeasonsRepository seasonsRepository;
    private final EpisodesRepository episodesRepository;

    private final UserRepository userRepository;

    @Autowired
    public SeriesServiceImpl(UserRepository userRepository, UserSeriesRepository userSeriesRepository, SeriesRepository seriesRepository, SeasonsRepository seasonsRepository, EpisodesRepository episodesRepository) {
        this.userSeriesRepository = userSeriesRepository;
        this.seriesRepository = seriesRepository;
        this.userRepository =userRepository;
        this.seasonsRepository=seasonsRepository;
        this.episodesRepository = episodesRepository;
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
    public List<Series> findAllWatchLaterSeriesForUser(String username) {
        RepoUser user = userRepository.findByUsername(username);
        List<UserSeries> seriesIds =  userSeriesRepository.getAllWatchLaterSeriesByUser(user);
        List<Series> series= new ArrayList<>();
        seriesIds.forEach(seriesId -> {
            series.add(seriesId.getUserSeriesId().getSeries());
        });
        return series;
    }


    @Override
    public Boolean watchLaterSeries(String username, Integer seriesId) {
        RepoUser user = userRepository.findByUsername(username);
        Optional<Series> series = seriesRepository.findById(seriesId);
        if (series.isPresent()){
            Optional<UserSeries> watchLaterSeriesOptional = userSeriesRepository.findById(new UserSeriesId(user,series.get()));
            if(watchLaterSeriesOptional.isPresent()) {
                UserSeries userSeries =  watchLaterSeriesOptional.get();
                if(userSeries.getWatchLater()){
                    userSeries.setWatchLater(false);
                    userSeries.setAddedDate(null);
                }
                else{
                    userSeries.setWatchLater(true);
                    userSeries.setAddedDate(LocalDateTime.now());
                }
                this.userSeriesRepository.save(userSeries);
                return userSeries.getWatchLater();
            }
            else {
                UserSeries userSeries = new UserSeries(new UserSeriesId(user, series.get()), LocalDateTime.now() ,true, null, false, 0, null);
                userSeriesRepository.save(userSeries);
                return true;
            }
        }
        return false;
    }
    @Override
    public SerialDetails getDetailsForASerial(String serialName) {
        SerialDetails serialDetails;
        Optional<Series> serial = this.seriesRepository.getSerialByName(serialName);
        if (serial.isPresent()){
            serialDetails = ConvertData.convertSerialToSerialDetails(serial.get());
            return serialDetails;
        }
        return null;
    }

    @Override
    public Boolean setSeriesRating(String username, Integer seriesId, Integer rating) {
        RepoUser user = userRepository.findByUsername(username);
        Optional<Series> series = seriesRepository.findById(seriesId);
        if (series.isPresent()){
            Optional<UserSeries> watchLaterSeriesOptional = userSeriesRepository.findById(new UserSeriesId(user,series.get()));
            if(watchLaterSeriesOptional.isPresent()) {
                UserSeries userSeries =  watchLaterSeriesOptional.get();
                userSeries.setRating(rating);
                this.userSeriesRepository.save(userSeries);
                return true;
            }
            else {
                UserSeries userSeries = new UserSeries(new UserSeriesId(user, series.get()), null ,false, null, false, rating, null);
                userSeriesRepository.save(userSeries);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<SeriesHistory> findAllHistorySeriesForUser(String username) {
        RepoUser user = userRepository.findByUsername(username);
        List<UserSeries> seriesIds =  userSeriesRepository.getAllHistorySeriesByUser(user);
        List<SeriesHistory> series= new ArrayList<>();
        seriesIds.forEach(seriesId -> {

            SeriesHistory seriesHistory = new SeriesHistory(seriesId.getUserSeriesId().getSeries().getId(),seriesId.getUserSeriesId().getSeries().getName(), seriesId.getUserSeriesId().getSeries().getImage(), seriesId.getEpisode().getSeason().getNumber(),seriesId.getEpisode().getNumber(), seriesId.getEpisode().getName());
            series.add(seriesHistory);
        });
        return series;
    }

    @Override
    public Boolean historySeries(String username, Integer seriesId, Integer episodeId) {
        RepoUser user = userRepository.findByUsername(username);
        Optional<Series> series = seriesRepository.findById(seriesId);
        Episode episode = this.episodesRepository.findById(episodeId).get();
        if (series.isPresent()){
            Optional<UserSeries> watchLaterSeriesOptional = userSeriesRepository.findById(new UserSeriesId(user,series.get()));
            if(watchLaterSeriesOptional.isPresent()) {
                UserSeries userSeries =  watchLaterSeriesOptional.get();
                if(userSeries.getHistory() && userSeries.getEpisode() == episode){
                    userSeries.setHistory(false);
                    userSeries.setWatchedDate(null);
                    userSeries.setEpisode(null);
                }
                else{
                    userSeries.setHistory(true);
                    userSeries.setWatchedDate(LocalDateTime.now());
                    userSeries.setEpisode(episode);
                }
                this.userSeriesRepository.save(userSeries);
                return userSeries.getWatchLater();
            }
            else {
                UserSeries userSeries = new UserSeries(new UserSeriesId(user, series.get()), null,false, LocalDateTime.now(), true, 0, episode);
                userSeriesRepository.save(userSeries);
                return true;
            }
        }
        return false;
    }

}
