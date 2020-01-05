package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }
}
