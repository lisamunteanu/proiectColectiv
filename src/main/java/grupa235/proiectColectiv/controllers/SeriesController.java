package grupa235.proiectColectiv.controllers;

import grupa235.proiectColectiv.frontendModel.SerialModel;
import grupa235.proiectColectiv.model.Series;
import grupa235.proiectColectiv.services.impl.SeriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = {"*"}
)
public class SeriesController {

    private final SeriesServiceImpl seriesService;

    @Autowired
    public SeriesController(SeriesServiceImpl seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping({"/series"})
    public ResponseEntity<List<Series>> getAllSeries(){
        List<Series> series = this.seriesService.getAllSeries();
        return new ResponseEntity<>(series, HttpStatus.OK);
    }

    @GetMapping({"/series/{serialId}"})
    public ResponseEntity<?> getSerialById(@PathVariable String serialId){
        try {
            Series serial = this.seriesService.findSerialById(Integer.parseInt(serialId));
            return new ResponseEntity<>(serial, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping({"/series"})
    public ResponseEntity<Series> addSerial(@RequestBody SerialModel serialModel){
        Series serial = this.seriesService.addSerial(serialModel);
        return new ResponseEntity<>(serial,HttpStatus.OK);
    }

    @PutMapping({"/series"})
    public ResponseEntity<?> updateSerial(@RequestBody Series serial){
        try {
            Series updatedSerial = this.seriesService.updateSerial(serial);
            return new ResponseEntity<>(updatedSerial, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
