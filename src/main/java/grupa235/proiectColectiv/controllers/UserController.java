package grupa235.proiectColectiv.controllers;


import grupa235.proiectColectiv.frontendModel.Message;
import grupa235.proiectColectiv.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(
        origins = {"*"}
)
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping({"/friends"})
    public ResponseEntity<?> getFriends(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userTokenName = userDetails.getUsername();
        try {
            List<String> names = this.userService.getFriends(userTokenName);
            if (names != null)
                return new ResponseEntity<>(names, HttpStatus.OK);
            else
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping({"/search"})
    public ResponseEntity<?> getNewFriends(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userTokenName = userDetails.getUsername();
        try {
            List<String> names = this.userService.searchForNewFriends(userTokenName);
            return new ResponseEntity<>(names, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.OK);
        }
    }

    
}
