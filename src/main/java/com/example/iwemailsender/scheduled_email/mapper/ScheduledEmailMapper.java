package com.example.iwemailsender.scheduled_email.mapper;

import com.example.iwemailsender.infrastructure.mapper.GeneralMapper;
import com.example.iwemailsender.scheduled_email.domain.ScheduledEmail;
import com.example.iwemailsender.scheduled_email.dto.ScheduledEmailPojo;

public interface ScheduledEmailMapper extends GeneralMapper<ScheduledEmailPojo, ScheduledEmail> {
    public void mapRequestedFieldForUpdate(ScheduledEmail entity, ScheduledEmailPojo dto);
}
