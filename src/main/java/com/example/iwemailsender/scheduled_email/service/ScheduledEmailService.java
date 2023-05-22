package com.example.iwemailsender.scheduled_email.service;

import com.example.iwemailsender.scheduled_email.domain.ScheduledEmail;
import com.example.iwemailsender.scheduled_email.dto.ScheduledEmailPojo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScheduledEmailService {
    public ScheduledEmailPojo findById(Long id);
    public ScheduledEmailPojo getByUuid(String uuid);

    public ScheduledEmail findByUuid(String uuid);

    public List<ScheduledEmailPojo> getAll();

    public ScheduledEmailPojo createTemplate(ScheduledEmailPojo scheduledEmailPojo);

    public ScheduledEmailPojo updateTemplate(String uuid, ScheduledEmailPojo scheduledEmailPojo);

    public void removeEmployee(String uuid);
}
