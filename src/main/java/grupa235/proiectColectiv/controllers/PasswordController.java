package grupa235.proiectColectiv.controllers;

import grupa235.proiectColectiv.frontendModel.UserDTO;
import grupa235.proiectColectiv.model.RepoUser;
import grupa235.proiectColectiv.services.EmailService;
import grupa235.proiectColectiv.services.UserService;
import grupa235.proiectColectiv.services.impl.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

@RestController
public class PasswordController {

    @Autowired
    private UserService userService;

    //@Autowired
    private EmailService emailService;

    //@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //display forgot password page
    @RequestMapping(value="/forgot",method = RequestMethod.GET)
    public ModelAndView displayforgotPasswordPage(){
        return new ModelAndView("forgotPassword");
    }

    //process form submission from forgot password page
    @RequestMapping(value="/forgot",method = RequestMethod.POST)
    public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("email")  String userEmail, HttpServletRequest request){
        RepoUser found = userService.findUserByEmail(userEmail);
        if(found==null)
            modelAndView.addObject("errorMessage","No account for the e-mail.");
        else
        {
            //generate random 36-character string token for reset password
            found.setResetToken(UUID.randomUUID().toString());

            //save the new token for the user in database
            userService.save(found);

            String appUrl = request.getScheme()+"://"+request.getServerName();

            //email message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("bingewatch@movies.com");
            passwordResetEmail.setTo(found.getUsername());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl + "/reset?token="+found.getResetToken());
            emailService.sendEmail(passwordResetEmail);

            modelAndView.addObject("successMessage","A password reset link has been sent to "+ userEmail);

        }
        modelAndView.setViewName("forgotPassword");
        return modelAndView;
    }

    @RequestMapping(value="/reset",method = RequestMethod.GET)
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView , @RequestParam("token") String token){
        RepoUser user = userService.findUserByResetToken(token);

        if(user!=null)
            modelAndView.addObject("resetToken",token);
        else
            modelAndView.addObject("errorMessage","Invalid password reset link");

        modelAndView.setViewName("resetPassword");
        return modelAndView;
    }
    @RequestMapping(value="/reset",method = RequestMethod.POST)
    public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams, RedirectAttributes redir){
        RepoUser user = userService.findUserByResetToken(requestParams.get("token"));

        if(user!=null)
        {
            user.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
            //the token will be set to null so it cannot be used again
            user.setResetToken(null);

            //save the user
            userService.save(user);

            // In order to set a model attribute on a redirect, we must use
            // RedirectAttributes
            redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");

            modelAndView.setViewName("redirect:login");
            return modelAndView;

        } else {
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
            modelAndView.setViewName("resetPassword");
        }

        return modelAndView;
    }

    // Going to reset page without a token redirects to login page
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
        return new ModelAndView("redirect:login");
    }
}
