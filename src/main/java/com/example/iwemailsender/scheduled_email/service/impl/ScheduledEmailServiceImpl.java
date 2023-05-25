package com.example.iwemailsender.scheduled_email.service.impl;

import com.example.iwemailsender.infrastructure.exception.ResourceNotFoundException;
import com.example.iwemailsender.scheduled_email.domain.ScheduledEmail;
import com.example.iwemailsender.scheduled_email.dto.ScheduledEmailPojo;
import com.example.iwemailsender.scheduled_email.mapper.ScheduledEmailMapper;
import com.example.iwemailsender.scheduled_email.repository.ScheduledEmailRepository;
import com.example.iwemailsender.scheduled_email.service.ScheduledEmailService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class ScheduledEmailServiceImpl implements ScheduledEmailService {
    @Autowired
    ScheduledEmailRepository scheduledEmailRepository;
    @Autowired
    ScheduledEmailMapper scheduledEmailMapper;

    @Override
    public ScheduledEmailPojo findById(Long id) {
        ScheduledEmail scheduledEmail = scheduledEmailRepository.findById(id).orElseThrow(()->{
            log.error("Scheduled email with id {} is not found", id);
            return new ResourceNotFoundException("Scheduled email not found");
        });
        return scheduledEmailMapper.entityToDto(scheduledEmail);
    }

    @Override
    public ScheduledEmailPojo getByUuid(String uuid) {
        log.debug("Execute getByUuid with parameter {}", uuid);
        return scheduledEmailMapper.entityToDto(findByUuid(uuid));    }

    @Override
    public ScheduledEmail findByUuid(String uuid) {
        log.debug("Execute findByUuid with parameter {}", uuid);
        return scheduledEmailRepository.findByUuid(uuid).orElseThrow(()->{
            log.error("Error: Resource scheduled email with uuid {} is not found", uuid);
            return new ResourceNotFoundException("Scheduled email not found");
        });
    }

    @Override
    public List<ScheduledEmailPojo> getAll() {
        return scheduledEmailMapper.mapList(scheduledEmailRepository.findAll(), ScheduledEmailPojo.class);
    }

    @Override
    public ScheduledEmailPojo createTemplate(ScheduledEmailPojo scheduledEmailPojo) {
        log.debug("Execute create scheduled email with parameters ", scheduledEmailPojo);
        ScheduledEmail createScheduledEmail = scheduledEmailMapper.dtoToEntity(scheduledEmailPojo);
        ScheduledEmail savingScheduledEmail = scheduledEmailRepository.save(createScheduledEmail);

        return scheduledEmailMapper.entityToDto(savingScheduledEmail);    }

    @Override
    public ScheduledEmailPojo updateTemplate(String uuid, ScheduledEmailPojo scheduledEmailPojo) {
        log.debug("Execute scheduled email with parameter {}", scheduledEmailPojo);
        ScheduledEmail targetScheduledEmail = findByUuid(uuid);
        scheduledEmailMapper.mapRequestedFieldForUpdate(targetScheduledEmail, scheduledEmailPojo);

        return scheduledEmailMapper.entityToDto(scheduledEmailRepository.saveAndFlush(targetScheduledEmail));    }

    @Override
    public void removeEmployee(String uuid) {
        ScheduledEmail scheduledEmail = findByUuid(uuid);
        scheduledEmailRepository.delete(scheduledEmail);
    }
}
