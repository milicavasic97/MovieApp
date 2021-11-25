package com.webapi.movieapp.service;

import com.webapi.movieapp.models.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    private final MailSender mailSender;

    public EmailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailSender.getOrigin());
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.getJavaMailSender().send(message);
    }
}
