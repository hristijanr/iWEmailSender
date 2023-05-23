package com.example.iwemailsender.email_sender.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailSender {

    private String to;
    private String subject;
    private String message;

    public EmailSender() {
    }

    public EmailSender(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

}
