package com.ju.islamicculturalcenter.service.impl;

import com.ju.islamicculturalcenter.service.iservice.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendPasswordEmail(String firstName, String emailTo, String password) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(emailTo);
        message.setSubject("Islamic-Cultural-Center Account Password");
        message.setText("Hello, " + firstName + "!\n"
                + "Your Password Is :" + password + "\n"
                + "Best Regards, Islamic-cultural-center!");

        try {
            javaMailSender.send(message);
            log.info("Email Sent Successfully to : {}", emailTo);
        } catch (MailException e) {
            log.info(e.getMessage());
        }
    }
}
