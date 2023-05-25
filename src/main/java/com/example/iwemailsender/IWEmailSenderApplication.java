package com.example.iwemailsender;

import com.example.iwemailsender.employee.repository.EmployeeRepository;
import com.example.iwemailsender.email_template.repository.EmailTemplateRepository;
import com.example.iwemailsender.scheduled_email.repository.ScheduledEmailRepository;
import com.example.iwemailsender.email_sender.domain.EmailSender;
import com.example.iwemailsender.email_sender.service.EmailSenderService;
import com.example.iwemailsender.email_template.domain.EmailTemplate;
import com.example.iwemailsender.employee.domain.Employee;
import com.example.iwemailsender.scheduled_email.domain.ScheduledEmail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@SpringBootApplication
public class IWEmailSenderApplication {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Autowired
    private ScheduledEmailRepository scheduledEmailRepository;

    @Autowired
    private EmailSenderService emailSenderService;


    public static void main(String[] args) {
        SpringApplication.run(IWEmailSenderApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeData() {
        Employee employee = new Employee("Hristijan Rahmanov", "hristijanrahmanov9595@gmail.com", "Backend Developer",
                LocalDate.of(2021, Month.JULY, 20).atStartOfDay(), null);

        //proverka za dali pravilno raboti uslovot za endDate
        //        LocalDate.of(2021, Month.JULY, 20).atStartOfDay(), LocalDate.of(2021, Month.JULY, 20).atStartOfDay());

        employeeRepository.save(employee);

        EmailTemplate emailTemplate = new EmailTemplate("Test email", "Test subject", "Dear " + employee.getName() + "\n" + "" +
                "bla bla bla bla...");
        emailTemplateRepository.save(emailTemplate);

        ScheduledEmail scheduledEmail = new ScheduledEmail(employee.getEmail(), LocalDateTime.now(), emailTemplate, employee);
        scheduledEmailRepository.save(scheduledEmail);

        if (employee != null && emailTemplate != null && scheduledEmail != null) {
            if (employee.getEndDate() != null){
                if (employee.getEndDate().isBefore(LocalDateTime.now())){
                    throw new IllegalArgumentException("This employee no longer works at the company.");
                }
            }
            if (employee.getRole().equals("Backend Developer")) {
                EmailSender emailSender = new EmailSender();
                emailSenderService.sendEmail(scheduledEmail.getRecipientEmail(), emailTemplate.getSubject(), emailTemplate.getTemplate());
            } else if (employee.getRole().equals("Frontend Developer")) {
                EmailSender emailSender = new EmailSender();
                emailSenderService.sendEmail(scheduledEmail.getRecipientEmail(), emailTemplate.getSubject(), emailTemplate.getTemplate());
            } else throw new IllegalArgumentException("Employee role is not Backend Developer");
        }
    }
}
