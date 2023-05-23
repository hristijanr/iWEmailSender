package com.example.iwemailsender;

import com.example.iwemailsender.email_sender.domain.EmailSender;
import com.example.iwemailsender.email_sender.service.EmailSenderService;
import com.example.iwemailsender.email_template.domain.EmailTemplate;
import com.example.iwemailsender.email_template.service.EmailTemplateService;
import com.example.iwemailsender.employee.domain.Employee;
import com.example.iwemailsender.scheduled_email.domain.ScheduledEmail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class IWEmailSenderApplication {


    public static void main(String[] args) {
        SpringApplication.run(IWEmailSenderApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

//    EmailTemplate emailTemplate = new EmailTemplate("Java-Release","Event","Template1");
//
//    Employee employee = new Employee("Hristijan","hristijanrahmanov9595@gmail.com","Backend-Developer");
//
//    ScheduledEmail scheduledEmail = new ScheduledEmail(employee.getEmail(), LocalDateTime.now(),emailTemplate,employee);
//
//    EmailSender emailSender = new EmailSender(scheduledEmail.getRecipientEmail(), emailTemplate.getSubject(), "ZDRAVO");

}
