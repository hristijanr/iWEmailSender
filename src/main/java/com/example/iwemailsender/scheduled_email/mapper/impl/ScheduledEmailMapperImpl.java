package com.example.iwemailsender.scheduled_email.mapper.impl;

import com.example.iwemailsender.infrastructure.mapper.AbstractGeneralMapper;
import com.example.iwemailsender.scheduled_email.domain.ScheduledEmail;
import com.example.iwemailsender.scheduled_email.dto.ScheduledEmailPojo;
import com.example.iwemailsender.scheduled_email.mapper.ScheduledEmailMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduledEmailMapperImpl extends AbstractGeneralMapper implements ScheduledEmailMapper {
    @Autowired
    public ScheduledEmailMapperImpl(ModelMapper modelMapper){
        super(modelMapper);
    }

    @Override
    public ScheduledEmailPojo entityToDto(ScheduledEmail scheduledEmail){
        return this.modelMapper.map(scheduledEmail, ScheduledEmailPojo.class);
    }

    @Override
    public ScheduledEmail dtoToEntity(ScheduledEmailPojo scheduledEmailPojo){
        return this.modelMapper.map(scheduledEmailPojo, ScheduledEmail.class);
    }

    public void mapRequestedFieldForUpdate(ScheduledEmail entity, ScheduledEmailPojo dto){
        entity.setRecipientEmail(dto.getRecipientEmail());
        entity.setSendDate(dto.getSendDate());
        entity.setEmailTemplate(dto.getEmailTemplate());
        entity.setEmployee(dto.getEmployee());
    }
}
