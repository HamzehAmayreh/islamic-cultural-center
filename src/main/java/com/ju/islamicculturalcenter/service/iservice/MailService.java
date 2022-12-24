package com.ju.islamicculturalcenter.service.iservice;

public interface MailService {

    void sendPasswordEmail(String firstName, String emailTo, String password);
}
