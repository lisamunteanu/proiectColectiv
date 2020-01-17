package grupa235.proiectColectiv.controllers;

import grupa235.proiectColectiv.frontendModel.UserDTO;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.services.EmailService;
import grupa235.proiectColectiv.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
public class PasswordController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @PostMapping(value="/resetCode",consumes = {"application/json"})
    public ResponseEntity<?> getCode(@RequestBody String userEmail)
    {
        Optional<RepoUser> found = userService.findUserByEmail(userEmail);
        if(!found.isPresent())
        {
            RepoUser repoUser = found.get();
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setTo(repoUser.getUsername());
            passwordResetEmail.setSubject("Password Reset Code");
            //5 digits code
            int code = (int)(Math.random() * ((99999 - 10000) + 1)) + 10000;
            passwordResetEmail.setText(String.valueOf(code));
            emailService.sendEmail(passwordResetEmail);

            return ResponseEntity.ok(userService.save(repoUser));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping(value="/changePassword",consumes = {"application/json"})
    public ResponseEntity<?> submitChangePassword(@RequestBody UserDTO user)
    {
        Optional<RepoUser> found = userService.findUserByEmail(user.getUsername());
        if(!found.isPresent())
        {
            RepoUser repoUser = found.get();
            repoUser.setResetToken(UUID.randomUUID().toString());
            repoUser.setPassword(bcryptEncoder.encode(user.getPassword()));
            RepoUser keepUser = userService.save(repoUser);
            return ResponseEntity.ok(userService.save(keepUser));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }




}
