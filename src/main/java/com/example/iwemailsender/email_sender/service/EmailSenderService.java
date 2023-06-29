package com.example.iwemailsender.email_sender.service;

import org.springframework.stereotype.Service;

@Service

public interface EmailSenderService {
    public void sendEmail(String to, String subject, String message);

    public void sendSMS(String to, String message);
}
