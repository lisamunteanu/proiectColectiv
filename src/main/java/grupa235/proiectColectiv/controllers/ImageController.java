package grupa235.proiectColectiv.controllers;


import grupa235.proiectColectiv.frontendModel.Image;
import grupa235.proiectColectiv.frontendModel.Message;
import grupa235.proiectColectiv.services.UserImageService;
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
public class ImageController {

    private final UserImageService userImageService;

    @Autowired
    public ImageController(UserImageService userImageService) {
        this.userImageService = userImageService;
    }


    @GetMapping({"/image"})
    public ResponseEntity<?> getImage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        Image image = this.userImageService.getImage(userName);
        if (image!=null){
            return new ResponseEntity<>(image, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Message("No image available!"),HttpStatus.OK);
    }

    @PostMapping({"/image"})
    public ResponseEntity<Image> saveImage(@RequestBody Image image){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        Image saveImage = this.userImageService.saveImage(userName,image.getImage());
        return new ResponseEntity<>(saveImage,HttpStatus.OK);
    }
}
