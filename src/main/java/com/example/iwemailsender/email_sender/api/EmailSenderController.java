package com.example.iwemailsender.email_sender.api;

import com.example.iwemailsender.email_sender.domain.EmailSender;
import com.example.iwemailsender.email_sender.service.EmailSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailSenderController {

    private final EmailSenderService emailSenderService;

    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestBody EmailSender emailSender){
        this.emailSenderService.sendEmail(emailSender.getTo(), emailSender.getSubject(), emailSender.getMessage());
        return ResponseEntity.ok("Success");
    }
}
