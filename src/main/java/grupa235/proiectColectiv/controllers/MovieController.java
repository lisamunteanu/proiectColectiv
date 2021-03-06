package grupa235.proiectColectiv.controllers;

import grupa235.proiectColectiv.frontendModel.*;
import grupa235.proiectColectiv.model.Friends;
import grupa235.proiectColectiv.model.Movie;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.services.FriendsService;
import grupa235.proiectColectiv.services.MovieService;
import grupa235.proiectColectiv.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin(
        origins = {"*"}
)

public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    FriendsService friendsService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/movies")
    public ResponseEntity<List<MovieModel>> findAllMovies() {
        List<MovieModel> allMovies = movieService.findAllMovies();
        return new ResponseEntity<>(allMovies, HttpStatus.OK);
    }

    @GetMapping(value = "movies/{id}")
    public ResponseEntity<Map<String, MovieModel>> findMovieById(@PathVariable String id) {
        MovieModel movie = movieService.findById(Integer.parseInt(id));
        Map<String, MovieModel> response = new HashMap<>();
        if (movie!=null) {
            response.put("Movie found", movie);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Movie not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "movies/details/{movieName}")
    public ResponseEntity<?> getDetailsForAMovie(@PathVariable String movieName){
        MovieDetails movieDetails = this.movieService.getDetailsForAMovie(movieName);
        if (movieDetails!=null){
            return new ResponseEntity<>(movieDetails,HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("There is not a movie with this name!"),HttpStatus.BAD_REQUEST);
    }
    @GetMapping(value = "movies/watch-later")
    public ResponseEntity<List<Movie>> findAllWatchLaterMovies(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentPrincipalName = (UserDetails) authentication.getPrincipal();
        String username = currentPrincipalName.getUsername();
        List<Movie> allMovies = movieService.findAllWatchLaterForUser(username);
//        List<MovieModel> convertedMovies = new ArrayList<>();
//        for (Movie m : allMovies) {
//            convertedMovies.add(ConvertData.convertMovieToMovieModel(m));
//        }
        return new ResponseEntity<>(allMovies, HttpStatus.OK);
    }

    @PostMapping(value = "movies/{id}/watch-later")
    public ResponseEntity<?> modifyWatchLaterMovie(@PathVariable String id) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentPrincipalName = (UserDetails) authentication.getPrincipal();
        String username = currentPrincipalName.getUsername();
        Boolean status = this.movieService.watchLaterMovie(username, Integer.parseInt(id));
        return new ResponseEntity<>(new BooleanModel(status),HttpStatus.OK);
    }

    @GetMapping(value = "movies/history")
    public ResponseEntity<List<Movie>> findAllMovieHistory(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentPrincipalName = (UserDetails) authentication.getPrincipal();
        String username = currentPrincipalName.getUsername();
        List<Movie> allMovies = movieService.findAllMovieHistoryForUser(username);
//        List<MovieModel> convertedMovies = new ArrayList<>();
//        for (Movie m : allMovies) {
//            convertedMovies.add(ConvertData.convertMovieToMovieModel(m));
//        }
        return new ResponseEntity<>(allMovies, HttpStatus.OK);
    }

    @PostMapping(value = "movies/{id}/history")
    public ResponseEntity<?> modifyMovieHistory(@PathVariable String id) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentPrincipalName = (UserDetails) authentication.getPrincipal();
        String username = currentPrincipalName.getUsername();
        Boolean status = this.movieService.movieHistory(username, Integer.parseInt(id));
        return new ResponseEntity<>(new BooleanModel(status),HttpStatus.OK);
    }

    @PostMapping(value = "movies/{id}/rate")
    public ResponseEntity<?> modifyMovieRating(@PathVariable String id, @RequestBody RateModel rating) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentPrincipalName = (UserDetails) authentication.getPrincipal();
        String username = currentPrincipalName.getUsername();
        Boolean status = this.movieService.setMovieRating(username, Integer.parseInt(id),rating.getRating());
        return new ResponseEntity<>(new BooleanModel(status),HttpStatus.OK);
    }

    @GetMapping(value = "movies/recommended")
    public ResponseEntity<?> recommendedMovies() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentPrincipalName = (UserDetails) authentication.getPrincipal();
        String username = currentPrincipalName.getUsername();
        Optional<List<Friends>> friends = friendsService.findAllFriendsForUser(username);

        List<Friends> friendsList = new ArrayList<>();
        List<Movie> friendHistory = new ArrayList<>();

        if(friends.isPresent())
            friendsList = friends.get();

        for (Friends friend : friendsList) {
            String friendUsername ;
            if(friend.getFirstUser().equals(username))
                friendUsername = friend.getSecondUser().getUsername();
            else
                friendUsername = friend.getFirstUser().getUsername();
            friendHistory.addAll(movieService.findAllMovieHistoryForUser(friendUsername));
        }
        List<Movie> recommendedMovies = new ArrayList<>();
        int noRecommendedMovies = friendHistory.size();
        if(noRecommendedMovies>10)
            noRecommendedMovies=10;
        Random random = new Random();
        while(noRecommendedMovies!=0)
        {
            recommendedMovies.add(friendHistory.get(random.nextInt(friendHistory.size())));
            noRecommendedMovies--;
        }
        return new ResponseEntity<>(recommendedMovies,HttpStatus.OK);
    }

}
