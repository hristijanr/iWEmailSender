package com.example.iwemailsender.email_template.service;

import com.example.iwemailsender.email_template.domain.EmailTemplate;
import com.example.iwemailsender.email_template.dto.EmailTemplatePojo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmailTemplateService {
    public EmailTemplatePojo findById(Long id);
    public EmailTemplatePojo getByUuid(String uuid);

    public EmailTemplate findByUuid(String uuid);

    public List<EmailTemplatePojo> getAll();

    public EmailTemplatePojo createTemplate(EmailTemplatePojo emailTemplatePojo);

    public EmailTemplatePojo updateTemplate(String uuid, EmailTemplatePojo emailTemplatePojo);

    public void removeEmailTemplate(String uuid);

}
