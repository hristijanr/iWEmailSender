package com.example.iwemailsender.scheduled_email.domain;

import com.example.iwemailsender.email_template.domain.EmailTemplate;
import com.example.iwemailsender.employee.domain.Employee;
import com.example.iwemailsender.infrastructure.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "scheduled_emails")
public class ScheduledEmail extends BaseEntity {

    @Column(name = "recipient_email", nullable = false, length = 100)
    private String recipientEmail;
    @Column(name = "send_date", nullable = false)
    private LocalDateTime sendDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", nullable = false)
    private EmailTemplate emailTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public ScheduledEmail(String recipientEmail, LocalDateTime sendDate, EmailTemplate emailTemplate, Employee employee) {
        this.recipientEmail = recipientEmail;
        this.sendDate = sendDate;
        this.emailTemplate = emailTemplate;
        this.employee = employee;
    }

    public ScheduledEmail() {
    }
}
