package com.example.iwemailsender.email_sender.service;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String message);
}
