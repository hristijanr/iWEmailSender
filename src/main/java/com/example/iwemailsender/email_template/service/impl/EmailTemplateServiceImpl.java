package com.example.iwemailsender.email_template.service.impl;


import com.example.iwemailsender.email_template.domain.EmailTemplate;
import com.example.iwemailsender.email_template.dto.EmailTemplatePojo;
import com.example.iwemailsender.email_template.mapper.EmailTemplateMapper;
import com.example.iwemailsender.email_template.repository.EmailTemplateRepository;
import com.example.iwemailsender.email_template.service.EmailTemplateService;
import com.example.iwemailsender.infrastructure.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@Slf4j
public class EmailTemplateServiceImpl implements EmailTemplateService {
    @Autowired
    EmailTemplateRepository emailTemplateRepository;

    @Autowired
    EmailTemplateMapper emailTemplateMapper;

    @Override
    public EmailTemplatePojo findById(Long id){
        EmailTemplate emailTemplateEntity = emailTemplateRepository.findById(id).orElseThrow(()->{
            log.error("Email template with id {} is not found", id);
            return new ResourceNotFoundException("Email template not found");
        });
        return emailTemplateMapper.entityToDto(emailTemplateEntity);
    }

    @Override
    public EmailTemplatePojo getByUuid(String uuid) {
        log.debug("Execute getByUuid with parameter {}", uuid);
        return emailTemplateMapper.entityToDto(findByUuid(uuid));
    }

    @Override
    public EmailTemplate findByUuid(String uuid) {
        log.debug("Execute findByUuid with parameter {}", uuid);
        return emailTemplateRepository.findByUuid(uuid).orElseThrow(()->{
           log.error("Error: Resource email template with uuid {} is not found", uuid);
           return new ResourceNotFoundException("Resource email template not found");
        });
    }

    @Override
    public List<EmailTemplatePojo> getAll() {
        return emailTemplateMapper.mapList(emailTemplateRepository.findAll(), EmailTemplatePojo.class);
    }

    @Override
    public EmailTemplatePojo createTemplate(EmailTemplatePojo emailTemplatePojo) {
        log.debug("Execute create book with parameters ", emailTemplatePojo);
        EmailTemplate createEmailTemplate = emailTemplateMapper.dtoToEntity(emailTemplatePojo);
        EmailTemplate savingEmailTemplate = emailTemplateRepository.save(createEmailTemplate);

        return emailTemplateMapper.entityToDto(savingEmailTemplate);
    }

    @Override
    public EmailTemplatePojo updateTemplate(String uuid, EmailTemplatePojo emailTemplatePojo) {
        log.debug("Execute email template with parameter {}", emailTemplatePojo);
        EmailTemplate targetEmailTemplate = findByUuid(uuid);
        emailTemplateMapper.mapRequestedFieldForUpdate(targetEmailTemplate, emailTemplatePojo);

        return emailTemplateMapper.entityToDto(emailTemplateRepository.saveAndFlush(targetEmailTemplate));
    }

    @Override
    public void removeEmailTemplate(String uuid) {
        EmailTemplate targetEmail = findByUuid(uuid);
        emailTemplateRepository.delete(targetEmail);
    }
}
