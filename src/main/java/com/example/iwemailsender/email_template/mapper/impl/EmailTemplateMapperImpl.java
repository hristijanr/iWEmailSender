package com.example.iwemailsender.email_template.mapper.impl;

import com.example.iwemailsender.email_template.domain.EmailTemplate;
import com.example.iwemailsender.email_template.dto.EmailTemplatePojo;
import com.example.iwemailsender.email_template.mapper.EmailTemplateMapper;
import com.example.iwemailsender.infrastructure.mapper.AbstractGeneralMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailTemplateMapperImpl extends AbstractGeneralMapper implements EmailTemplateMapper {
    @Autowired
    public EmailTemplateMapperImpl(ModelMapper modelMapper){

        super(modelMapper);
    }

    @Override
    public EmailTemplatePojo entityToDto(EmailTemplate emailTemplate){
        return this.modelMapper.map(emailTemplate, EmailTemplatePojo.class);
    }

    @Override
    public EmailTemplate dtoToEntity(EmailTemplatePojo emailTemplateDto){
        return this.modelMapper.map(emailTemplateDto, EmailTemplate.class);
    }

    public void mapRequestedFieldForUpdate(EmailTemplate entity, EmailTemplatePojo dto){
        entity.setName(dto.getName());
        entity.setSubject(dto.getSubject());
        entity.setTemplate(dto.getTemplate());
    }

}
