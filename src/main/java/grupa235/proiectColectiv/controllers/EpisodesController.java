package grupa235.proiectColectiv.controllers;

import grupa235.proiectColectiv.frontendModel.EpisodeModel;
import grupa235.proiectColectiv.model.Episode;
import grupa235.proiectColectiv.services.EpisodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = {"*"}
)
public class EpisodesController {

    private final EpisodesService episodesService;

    @Autowired
    public EpisodesController(EpisodesService episodesService) {
        this.episodesService = episodesService;
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
        Episode episode = this.episodesService.addEpisode(episodeModel);
        return new ResponseEntity<>(episode, HttpStatus.OK);
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
}
