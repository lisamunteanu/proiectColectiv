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
        RepoUser found = userService.findUserByEmail(userEmail);
        if(found!=null)
        {
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setTo(found.getUsername());
            passwordResetEmail.setSubject("Password Reset Code");
            //5 digits code
            int code = (int)(Math.random() * ((99999 - 10000) + 1)) + 10000;
            passwordResetEmail.setText(String.valueOf(code));
            emailService.sendEmail(passwordResetEmail);

            return ResponseEntity.ok(userService.save(found));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping(value="/changePassword",consumes = {"application/json"})
    public ResponseEntity<?> submitChangePassword(@RequestBody UserDTO user)
    {
        RepoUser found = userService.findUserByEmail(user.getUsername());
        if(found!=null)
        {
            found.setResetToken(UUID.randomUUID().toString());
            found.setPassword(bcryptEncoder.encode(user.getPassword()));
            RepoUser keepUser = userService.save(found);
            return ResponseEntity.ok(userService.save(keepUser));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }




}
