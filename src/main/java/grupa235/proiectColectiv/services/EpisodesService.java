package grupa235.proiectColectiv.services;

import grupa235.proiectColectiv.converter.ConvertData;
import grupa235.proiectColectiv.frontendModel.EpisodeModel;
import grupa235.proiectColectiv.model.Episode;
import grupa235.proiectColectiv.model.Season;
import grupa235.proiectColectiv.model.Series;
import grupa235.proiectColectiv.repository.EpisodesRepository;
import grupa235.proiectColectiv.repository.SeasonsRepository;
import grupa235.proiectColectiv.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EpisodesService {

    private final EpisodesRepository episodesRepository;
    private final SeasonsRepository seasonsRepository;
    private final SeriesRepository seriesRepository;

    @Autowired
    public EpisodesService(EpisodesRepository episodesRepository,SeasonsRepository seasonsRepository, SeriesRepository seriesRepository) {
        this.episodesRepository = episodesRepository;
        this.seasonsRepository=seasonsRepository;
        this.seriesRepository = seriesRepository;
    }

    public List<Episode> getAllEpisodes(){
        return this.episodesRepository.findAll();
    }

    public List<Episode> getAllEpisodesFromASerial(String idSerial){
        return this.episodesRepository.getAllEpisodeFromASeason(idSerial);
    }

    public Episode findEpisodeById(int id) throws Exception {
        return this.episodesRepository.findById(id).get();
    }

    @Transactional
    public Episode addEpisode(EpisodeModel episodeModel){
        Episode episode = ConvertData.convertEpisodeModelToEpisode(episodeModel);
        Optional<Series> optionalSerial = this.seriesRepository.getSerialByName(episodeModel.getSerialName());
        if(optionalSerial.isPresent()){
            Series serial = optionalSerial.get();
            Optional<Integer> idSeason = this.seasonsRepository.getLastSeasonId(serial.getId());
            int id = idSeason.get();
            Optional<Season> optionalSeason = this.seasonsRepository.findById(id);
            if(optionalSeason.isPresent()){
                Season saveSeason = optionalSeason.get();
                int noEpisodes = serial.getNoOfEpisodes();
                serial.setNoOfEpisodes(noEpisodes+1);
                this.seriesRepository.save(serial);
                episode.setSeason(saveSeason);
                Optional<Integer> episodeNumber = this.episodesRepository.getLasEpisodeNumber(saveSeason.getId());
                if(episodeNumber.isPresent()){
                    int episodes = episodeNumber.get();
                    episode.setNumber(episodes+1);
                }
                else{
                    episode.setNumber(1);
                }
                this.episodesRepository.save(episode);
            }
            else{
                return null;
            }
        }
        return episode;

    }

    @Transactional
    public Episode updateEpisode(Episode episode) throws Exception{
        Episode updatedEpisode = this.episodesRepository.findById(episode.getId()).get();
        if (updatedEpisode!=null){
            updatedEpisode.setNumber(episode.getNumber());
            updatedEpisode.setDuration(episode.getDuration());
            updatedEpisode.setDescription(episode.getDescription());
            updatedEpisode.setName(episode.getName());
            this.episodesRepository.save(updatedEpisode);
        }
        return updatedEpisode;
    }

}
