package grupa235.proiectColectiv.services.impl;

import grupa235.proiectColectiv.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private String mailPort;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @Override
    @Async
    public void sendEmail(SimpleMailMessage email)
    {
        JavaMailSenderImpl mSender = new JavaMailSenderImpl();
        mSender.setHost(mailHost);
        mSender.setPort(Integer.parseInt(mailPort));
        mSender.setUsername(mailUsername);
        mSender.setPassword(mailPassword);

        Properties props = mSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required","false");
        props.put("mail.debug", "true");

        this.mailSender=mSender;
        mailSender.send(email);
    }
}
