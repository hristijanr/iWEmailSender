package com.example.iwemailsender.email_template.mapper;

import com.example.iwemailsender.email_template.domain.EmailTemplate;
import com.example.iwemailsender.email_template.dto.EmailTemplatePojo;
import com.example.iwemailsender.infrastructure.mapper.GeneralMapper;

public interface EmailTemplateMapper extends GeneralMapper<EmailTemplatePojo, EmailTemplate> {
    public void mapRequestedFieldForUpdate(EmailTemplate entity, EmailTemplatePojo dto);
}
