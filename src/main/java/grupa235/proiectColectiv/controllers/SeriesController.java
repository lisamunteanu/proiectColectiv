package grupa235.proiectColectiv.controllers;

import grupa235.proiectColectiv.frontendModel.*;
import grupa235.proiectColectiv.model.Friends;
import grupa235.proiectColectiv.model.Series;
import grupa235.proiectColectiv.services.FriendsService;
import grupa235.proiectColectiv.services.impl.SeriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@CrossOrigin(
        origins = {"*"}
)
public class SeriesController {

    private final SeriesServiceImpl seriesService;

    private final FriendsService friendsService;

    @Autowired
    public SeriesController(SeriesServiceImpl seriesService, FriendsService friendsService) {
        this.seriesService = seriesService;
        this.friendsService = friendsService;
    }

    @GetMapping({"/series"})
    public ResponseEntity<List<SerialModel>> getAllSeries(){
        List<SerialModel> series = this.seriesService.getAllSeries();
        return new ResponseEntity<>(series, HttpStatus.OK);
    }

    @GetMapping({"/series/{serialId}"})
    public ResponseEntity<?> getSerialById(@PathVariable String serialId){
        try {
            SerialModel serial = this.seriesService.findSerialById(Integer.parseInt(serialId));
            return new ResponseEntity<>(serial, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping({"/series"})
    public ResponseEntity<Series> addSerial(@RequestBody SerialPostModel serialPostModel){
        Series serial = this.seriesService.addSerial(serialPostModel);
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
    @GetMapping(value = "series/watch-later")
    public ResponseEntity<List<Series>> findAllSeries(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentPrincipalName = (UserDetails) authentication.getPrincipal();
        String username = currentPrincipalName.getUsername();
        List<Series> allSeries = seriesService.findAllWatchLaterSeriesForUser(username);
        return new ResponseEntity<>(allSeries,HttpStatus.OK) ;

    }

    @PostMapping(value = "series/{id}/watch-later")
    public ResponseEntity<?> addWatchLaterSeries(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentPrincipalName = (UserDetails) authentication.getPrincipal();
        String username = currentPrincipalName.getUsername();
        Boolean status = this.seriesService.watchLaterSeries(username, Integer.parseInt(id));
        return new ResponseEntity<>(new BooleanModel(status),HttpStatus.OK);
    }

    @GetMapping(value = "/series/details/{serialName}")
    public ResponseEntity<?> getDetailsForASerial(@PathVariable String serialName){
        SerialDetails serialDetails = this.seriesService.getDetailsForASerial(serialName);
        if (serialDetails!=null){
            return new ResponseEntity<>(serialDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("This serial does not exist!"),HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "series/{id}/rate")
    public ResponseEntity<?> modifySeriesRating(@PathVariable String id, @RequestBody RateModel rating){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentPrincipalName = (UserDetails) authentication.getPrincipal();
        String username = currentPrincipalName.getUsername();
        Boolean status = this.seriesService.setSeriesRating(username, Integer.parseInt(id),rating.getRating());
        return new ResponseEntity<>(new BooleanModel(status),HttpStatus.OK);
    }

    @GetMapping(value = "series/history")
    public ResponseEntity<List<SeriesHistory>> findAllHistorySeries(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentPrincipalName = (UserDetails) authentication.getPrincipal();
        String username = currentPrincipalName.getUsername();
        List<SeriesHistory> allSeries = seriesService.findAllHistorySeriesForUser(username);
        return new ResponseEntity<>(allSeries,HttpStatus.OK) ;

    }
    @PostMapping(value = "series/{id}/history")
    public ResponseEntity<?> modifySeriesHistory(@PathVariable String id, @RequestBody EpisodeBodyForHistory rating){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentPrincipalName = (UserDetails) authentication.getPrincipal();
        String username = currentPrincipalName.getUsername();
        Boolean status = this.seriesService.historySeries(username, Integer.parseInt(id),rating.getEpisodeId());
        return new ResponseEntity<>(new BooleanModel(status),HttpStatus.OK);
    }

    @GetMapping(value = "series/recommended")
    public ResponseEntity<?> recommendedSeries() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentPrincipalName = (UserDetails) authentication.getPrincipal();
        String username = currentPrincipalName.getUsername();
        Optional<List<Friends>> friends = friendsService.findAllFriendsForUser(username);

        List<Friends> friendsList = new ArrayList<>();
        List<SeriesHistory> friendHistory = new ArrayList<>();

        if(friends.isPresent())
            friendsList = friends.get();

        for (Friends friend : friendsList) {
            String friendUsername ;
            if(friend.getFirstUser().equals(username))
                friendUsername = friend.getSecondUser().getUsername();
            else
                friendUsername = friend.getFirstUser().getUsername();
            friendHistory.addAll(seriesService.findAllHistorySeriesForUser(friendUsername));
        }
        List<SeriesHistory> recommendedSeries = new ArrayList<>();
        int noRecommendedSeries = friendHistory.size();
        if(noRecommendedSeries>10)
            noRecommendedSeries=10;
        Random random = new Random();
        while(noRecommendedSeries!=0)
        {
            recommendedSeries.add(friendHistory.get(random.nextInt(friendHistory.size())));
            noRecommendedSeries--;
        }
        return new ResponseEntity<>(recommendedSeries,HttpStatus.OK);
    }
}
