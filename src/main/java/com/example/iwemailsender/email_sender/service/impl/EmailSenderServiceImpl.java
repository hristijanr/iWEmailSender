package com.example.iwemailsender.email_sender.service.impl;

import com.example.iwemailsender.email_sender.service.EmailSenderService;
import com.example.iwemailsender.employee.domain.Employee;
import com.example.iwemailsender.scheduled_email.domain.ScheduledEmail;
import com.example.iwemailsender.scheduled_email.repository.ScheduledEmailRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender mailSender;

    @Autowired
    private ScheduledEmailRepository scheduledEmailRepository;

    private final String twilioAccountSid = "AC5b35a3b4dc526317113c4d9ff87ccff8";
    private final String twilioAuthToken = "ec3b53ffbc62647fb3dce445f12c58dc";
    private final String twilioPhoneNumber = "14175386711";

    public EmailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {
        if (!isValidEmail(to)) {
            throw new IllegalArgumentException("Invalid email address: " + to);
        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("hristijanrahmanov9595@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        try {
            this.mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            // Handle the failure of email sending
            throw new RuntimeException("Failed to send email.", e);
        }
    }

    @Override
    public void sendSMS(String to, String message) {
        if (!isValidPhoneNumber(to)) {
            throw new IllegalArgumentException("Invalid phone number: " + to);
        }

        Twilio.init(twilioAccountSid, twilioAuthToken);
        try {
            String smsContent = "Your message: " + message; // Modify the content of the SMS message here
            Message.creator(new PhoneNumber(to), new PhoneNumber(twilioPhoneNumber), smsContent).create();
        } catch (Exception e) {
            // Handle the failure of SMS sending
            throw new RuntimeException("Failed to send SMS.", e);
        }
    }

    @Autowired
    private EntityManager entityManager;


    @Scheduled(cron = "0 11 16 * * ?")
    public void sendScheduledEmails() {
        List<ScheduledEmail> scheduledEmails = scheduledEmailRepository.findAllWithTemplates();
        LocalDate currentDate = LocalDate.now();

        for (ScheduledEmail scheduledEmail : scheduledEmails) {
            Employee employee = entityManager.find(Employee.class, scheduledEmail.getEmployee().getId());
            String recipientEmail = scheduledEmail.getRecipientEmail();
            String recipientPhone = employee.getPhoneNumber();
            String subject = scheduledEmail.getEmailTemplate().getSubject();
            String message = scheduledEmail.getEmailTemplate().getTemplate();
            String frequency = scheduledEmail.getFrequency();
            LocalDateTime sendDate = scheduledEmail.getSendDate();
            int retryLimit = scheduledEmail.getRetryLimit();
            int retryCount = 0;
            boolean emailSent = false;

            if (sendDate.toLocalDate().equals(currentDate)) {
                if (scheduledEmail.getFrequency().equalsIgnoreCase("daily")) {
                        while (retryCount < retryLimit && !emailSent) {
                            try {
                                sendEmail(recipientEmail, subject, message);
                                scheduledEmail.setSendDate(getNextScheduledDate(sendDate, frequency));
                                emailSent = true;
                            } catch (Exception e) {
                                retryCount++;
                            }
                        }

                        if (!emailSent) {
                            // Retry limit reached, handle the error
                            throw new RuntimeException("Failed to send email after maximum retries.");
                        } else {
                            emailSent = false;
                        }
                    }else if (scheduledEmail.getFrequency().equalsIgnoreCase("weekly")) {
                        while (retryCount < retryLimit && !emailSent) {
                            try {
                                sendEmail(recipientEmail, subject, message);
                                scheduledEmail.setSendDate(getNextScheduledDate(sendDate, frequency));
                                emailSent = true;
                            } catch (Exception e) {
                                retryCount++;
                            }
                        if (!emailSent) {
                            // Retry limit reached, handle the error
                            throw new RuntimeException("Failed to send email after maximum retries.");
                        }
                    }
                } else if (scheduledEmail.getFrequency().equalsIgnoreCase("monthly")) {
                        while (retryCount < retryLimit && !emailSent) {
                            try {
                                sendEmail(recipientEmail, subject, message);
                                scheduledEmail.setSendDate(getNextScheduledDate(sendDate, frequency));
                                emailSent = true;
                            } catch (Exception e) {
                                retryCount++;
                            }
                        if (!emailSent) {
                            // Retry limit reached, handle the error
                            throw new RuntimeException("Failed to send email after maximum retries.");
                        }
                    }
                } else if (scheduledEmail.getFrequency().equalsIgnoreCase("yearly")) {
                        while (retryCount < retryLimit && !emailSent) {
                            try {
                                sendEmail(recipientEmail, subject, message);
                                scheduledEmail.setSendDate(getNextScheduledDate(sendDate, frequency));
                                emailSent = true;
                            } catch (Exception e) {
                                retryCount++;
                            }
                        if (!emailSent) {
                            // Retry limit reached, handle the error
                            throw new RuntimeException("Failed to send email after maximum retries.");
                        }
                    }
                }
                if (recipientPhone != null && !recipientPhone.isEmpty()) {
                    sendSMS(recipientPhone, message);
                }
                scheduledEmailRepository.save(scheduledEmail);
            }
        }
    }

    private LocalDateTime getNextScheduledDate(LocalDateTime sendDate, String frequency) {
        switch (frequency.toLowerCase()) {
            case "daily":
                return sendDate.plusDays(1);
            case "weekly":
                return sendDate.plusWeeks(1);
            case "monthly":
                return sendDate.plusMonths(1);
            case "yearly":
                return sendDate.plusYears(1);
            default:
                throw new IllegalArgumentException("Invalid frequency: " + frequency);
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneNumberRegex = "^389\\d{8}$";
        return phoneNumber != null && phoneNumber.matches(phoneNumberRegex);
    }
}
