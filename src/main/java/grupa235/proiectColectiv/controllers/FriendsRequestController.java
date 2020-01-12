package grupa235.proiectColectiv.controllers;

import grupa235.proiectColectiv.frontendModel.Message;
import grupa235.proiectColectiv.model.FriendsRequest;
import grupa235.proiectColectiv.services.FriendsRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(
        origins = {"*"}
)
public class FriendsRequestController {

    @Autowired
    FriendsRequestService friendsRequestService;

    @PostMapping({"/friendRequest/{userName}"})
    public ResponseEntity<?> friendsRequest(@PathVariable String userName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userTokenName = userDetails.getUsername();
        FriendsRequest friendsRequest;
        try {
            friendsRequest = this.friendsRequestService.sendFriendRequest(userTokenName,userName);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(friendsRequest,HttpStatus.OK);
    }

    @PostMapping({"/friendRequestCanceled/{userName}"})
    public ResponseEntity<?> cancelRequest(@PathVariable String userName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userTokenName = userDetails.getUsername();
        boolean ok = false;
        try {
            ok = this.friendsRequestService.cancelRequest(userTokenName,userName);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        if (ok){
            return new ResponseEntity<>(ok,HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("Server side Error"),HttpStatus.BAD_REQUEST);
    }

    @PostMapping({"/friendRequestAccepted/{userName}"})
    public ResponseEntity<?> acceptRequest(@PathVariable String userName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userTokenName = userDetails.getUsername();
        boolean ok = false;
        try {
            ok = this.friendsRequestService.acceptRequest(userTokenName,userName);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        if (ok)
            return new ResponseEntity<>(ok,HttpStatus.OK);
        else
            return new ResponseEntity<>(new Message("You are already friends!"),HttpStatus.OK);
    }
}
