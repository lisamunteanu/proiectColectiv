package grupa235.proiectColectiv.controllers;

import grupa235.proiectColectiv.frontendModel.Message;
import grupa235.proiectColectiv.frontendModel.SeasonDetails;
import grupa235.proiectColectiv.frontendModel.SeasonModel;
import grupa235.proiectColectiv.model.Season;
import grupa235.proiectColectiv.services.impl.SeasonsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = {"*"}
)
public class SeasonsController {

    private final SeasonsServiceImpl seasonsService;

    @Autowired
    public SeasonsController(SeasonsServiceImpl seasonsService) {
        this.seasonsService = seasonsService;
    }

    @GetMapping({"/seasons"})
    public ResponseEntity<List<Season>> getAllSesons(){
        List<Season> seasons = this.seasonsService.getAllSeasons();
        return new ResponseEntity<>(seasons, HttpStatus.OK);
    }

    @GetMapping({"/seasons/search/{serialId}"})
    public ResponseEntity<List<Season>> getAllSesonsFromASerial(@PathVariable String serialId){
        List<Season> seasons = this.seasonsService.getAllSeasonsFromASerial(serialId);
        return new ResponseEntity<>(seasons, HttpStatus.OK);
    }

    @GetMapping({"/seasons/{seasonId}"})
    public ResponseEntity<?> getSeasonById(@PathVariable String seasonId) {
        try{
        Season season = this.seasonsService.findSeasonById(Integer.parseInt(seasonId));
        return new ResponseEntity<>(season, HttpStatus.OK);
        } catch (Exception ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
         }
    }

    @PostMapping({"/seasons"})
    public ResponseEntity<?> addSeason(@RequestBody SeasonModel seasonModel){
        try {
            Season season = this.seasonsService.addSeason(seasonModel);
            return new ResponseEntity<>(season, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping({"/seasons"})
    public ResponseEntity<?> updateSeason(@RequestBody Season season){
        try {
            Season updatedSeason = this.seasonsService.updateSeason(season);
            return new ResponseEntity<>(updatedSeason, HttpStatus.OK);
        }  catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping({"/seasons/details/{seasonName}"})
    public ResponseEntity<?> getDetailsForASeason(@PathVariable String seasonName){
        SeasonDetails seasonDetails = this.seasonsService.getDetailsForASeason(seasonName);
        if (seasonDetails!=null){
            return new ResponseEntity<>(seasonDetails,HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("There is no a season available!"),HttpStatus.BAD_REQUEST);
    }
}
