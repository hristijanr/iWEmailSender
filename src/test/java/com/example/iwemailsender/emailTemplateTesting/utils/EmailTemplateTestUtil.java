package com.example.iwemailsender.emailTemplateTesting.utils;

import com.example.iwemailsender.email_template.domain.EmailTemplate;
import com.example.iwemailsender.email_template.dto.EmailTemplatePojo;

import java.util.Date;
import java.util.UUID;

public class EmailTemplateTestUtil {
    public static EmailTemplate createMockEmailTemplateEntity() {
        EmailTemplate mock = new EmailTemplate();
        mock.setCreatedOn(new Date());
        mock.setUuid(UUID.randomUUID().toString());
        mock.setName("Mock Template");
        mock.setSubject("Mock Subject");
        mock.setTemplate("Mock Template Content");
        return mock;
    }

    public static EmailTemplatePojo createMockEmailTemplatePojo() {
        EmailTemplatePojo mock = new EmailTemplatePojo();
        mock.setName("Mock Template");
        mock.setSubject("Mock Subject");
        mock.setTemplate("Mock Template Content");
        return mock;
    }
}
