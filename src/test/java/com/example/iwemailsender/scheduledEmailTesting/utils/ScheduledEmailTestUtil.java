package com.example.iwemailsender.scheduledEmailTesting.utils;

import com.example.iwemailsender.employee.domain.Employee;
import com.example.iwemailsender.email_template.domain.EmailTemplate;
import com.example.iwemailsender.scheduled_email.domain.ScheduledEmail;

import java.time.LocalDateTime;
import java.util.UUID;

public class ScheduledEmailTestUtil {

    public static ScheduledEmail createMockScheduledEmailEntity(Employee employee, EmailTemplate emailTemplate) {
        ScheduledEmail mock = new ScheduledEmail();
        mock.setRecipientEmail("mock@example.com");
        mock.setSendDate(LocalDateTime.now());
        mock.setEmailTemplate(emailTemplate);
        mock.setEmployee(employee);
        mock.setRetryLimit(3);
        mock.setFrequency("daily");
        return mock;
    }

    public static ScheduledEmail createMockScheduledEmailEntityWithUuid(Employee employee, EmailTemplate emailTemplate, String uuid) {
        ScheduledEmail mock = new ScheduledEmail();
        mock.setUuid(uuid);
        mock.setRecipientEmail("mock@example.com");
        mock.setSendDate(LocalDateTime.now());
        mock.setEmailTemplate(emailTemplate);
        mock.setEmployee(employee);
        mock.setRetryLimit(3);
        mock.setFrequency("daily");
        return mock;
    }
}
