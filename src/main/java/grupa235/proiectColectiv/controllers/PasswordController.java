package grupa235.proiectColectiv.controllers;

import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.services.EmailService;
import grupa235.proiectColectiv.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
public class PasswordController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value="/changePassword",consumes = {"application/json"})
    public ResponseEntity<?> changePassword(@RequestBody String userEmail){
        RepoUser found = userService.findUserByEmail(userEmail);
        if(found!=null)
        {
            //generate random 36-character string token for reset password
            //found.setResetToken(UUID.randomUUID().toString());

            //save the new token for the user in database
            RepoUser keepUser = userService.save(found);

            //email message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("bingewatch@gmail.com");
            passwordResetEmail.setTo(found.getUsername());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n"+ "http://localhost:8080" + "/reset?token="+found.getResetToken());
            emailService.sendEmail(passwordResetEmail);

            return ResponseEntity.ok(userService.save(keepUser));
        }
        return null;
    }


}
