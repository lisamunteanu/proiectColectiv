package grupa235.proiectColectiv.controllers;

import grupa235.proiectColectiv.frontendModel.EpisodeModel;
import grupa235.proiectColectiv.model.Episode;
import grupa235.proiectColectiv.services.impl.EpisodesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin(
        origins = {"*"}
)
public class EpisodesController {

    private final EpisodesServiceImpl episodesService;

    @Autowired
    public EpisodesController(EpisodesServiceImpl episodesServiceImpl) {
        this.episodesService = episodesServiceImpl;
    }

    @GetMapping({"/episodes"})
    public ResponseEntity<List<Episode>> getAllEpisodes(){
        List<Episode> episodes = this.episodesService.getAllEpisodes();
        return new ResponseEntity<>(episodes, HttpStatus.OK);
    }


    @GetMapping({"/episodes/search/{seasonId}"})
    public ResponseEntity<List<Episode>> getAllSesonsFromASerial(@PathVariable String seasonId){
        List<Episode> episodes = this.episodesService.getAllEpisodesFromASerial(seasonId);
        return new ResponseEntity<>(episodes, HttpStatus.OK);
    }

    @GetMapping({"/episodes/{seasonId}"})
    public ResponseEntity<?> getEpisodeById(@PathVariable String seasonId){
        try {
            Episode episode = this.episodesService.findEpisodeById(Integer.parseInt(seasonId));
            return new ResponseEntity<>(episode, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping({"/episodes"})
    public ResponseEntity<?> addEpisode(@RequestBody EpisodeModel episodeModel){
        try {
            Episode episode = this.episodesService.addEpisode(episodeModel);
            return new ResponseEntity<>(episode, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping({"/episodes"})
    public ResponseEntity<?> updateEpisode(@RequestBody Episode episode) {
        try {
        Episode updatedEpisode = this.episodesService.updateEpisode(episode);
        return new ResponseEntity<>(updatedEpisode, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    /////
    @GetMapping(value = "/episodes")
    public ResponseEntity<List<EpisodeModel>> SortEpisodesByAddedDate() {
        List<Episode> allEpisodes = episodesService.getAllEpisodes();
        List<EpisodeModel> convertedEpisodes = new ArrayList<>();

        Comparator<Episode> compareByAddedDate = Comparator.comparing(Episode :: getAddedDate);
        Collections.sort(allEpisodes, compareByAddedDate);
        //Collections.sort(allMovies, (p1, p2) -> Long.valueOf(p1.getAddedDate().getTime()).compareTo(p2.getAddedDate().getTime()));

        return new ResponseEntity<>(convertedEpisodes, HttpStatus.OK);
    }


}
