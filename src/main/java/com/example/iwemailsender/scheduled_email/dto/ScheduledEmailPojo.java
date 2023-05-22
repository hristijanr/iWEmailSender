package com.example.iwemailsender.scheduled_email.dto;

import com.example.iwemailsender.email_template.domain.EmailTemplate;
import com.example.iwemailsender.employee.domain.Employee;
import com.example.iwemailsender.infrastructure.pojo.BasePojo;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ScheduledEmailPojo extends BasePojo {
    @NotNull(message="recipient email value must not be null")
    private String recipientEmail;
    @NotNull(message="Sending date value must not be null")
    private LocalDateTime sendDate;
    @NotNull(message="Template value must not be null")
    private EmailTemplate emailTemplate;
    @NotNull(message="Employee value must not be null")
    private Employee employee;

    private Date modifiedOn;
}
