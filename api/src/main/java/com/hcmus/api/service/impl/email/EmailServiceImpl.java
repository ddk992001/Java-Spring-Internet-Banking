package com.hcmus.api.service.impl.email;

import com.hcmus.api.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void sendEmail(EmailDetail emailDetail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(sender);
        mailMessage.setTo(emailDetail.getRecipient());
        mailMessage.setText(emailDetail.getMessageBody());
        mailMessage.setSubject(emailDetail.getSubject());

        javaMailSender.send(mailMessage);
    }
}
