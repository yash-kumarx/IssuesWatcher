package com.gsoc.gsocwatcher.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("yashr17042006@gmail.com"); // same gmail as properties
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}