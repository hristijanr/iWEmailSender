package com.example.iwemailsender.emailTemplateTesting.integration.repository;

import com.example.iwemailsender.emailTemplateTesting.utils.EmailTemplateTestUtil;
import com.example.iwemailsender.email_template.domain.EmailTemplate;
import com.example.iwemailsender.email_template.repository.EmailTemplateRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class EmailTemplateRepositoryTest {

    @Autowired
    EmailTemplateRepository emailTemplateRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(emailTemplateRepository).isNotNull();
    }

    @Test
    public void getAllEmailTemplates() {
        // Create a mock email template
        EmailTemplate mockTemplate = EmailTemplateTestUtil.createMockEmailTemplateEntity();
        emailTemplateRepository.save(mockTemplate);

        // Retrieve all email templates from the repository
        List<EmailTemplate> templates = emailTemplateRepository.findAll();

        // Assert that the retrieved list is not empty
        assertThat(templates).isNotEmpty();

        // Assert that the mock email template is present in the retrieved list
        assertThat(templates).contains(mockTemplate);
    }

    @Test
    public void getEmailTemplateById() {
        // Create a mock email template
        EmailTemplate mockTemplate = EmailTemplateTestUtil.createMockEmailTemplateEntity();
        emailTemplateRepository.save(mockTemplate);

        // Retrieve the email template by ID
        Optional<EmailTemplate> retrievedTemplate = emailTemplateRepository.findById(mockTemplate.getId());

        // Assert that the retrieved template is present and matches the mock template
        assertThat(retrievedTemplate).isPresent();
        assertThat(retrievedTemplate.get()).isEqualTo(mockTemplate);
    }

    @Test
    public void createNewEmailTemplate() {
        // Create a new email template
        EmailTemplate newTemplate = EmailTemplateTestUtil.createMockEmailTemplateEntity();

        // Save the new template to the repository
        EmailTemplate savedTemplate = emailTemplateRepository.save(newTemplate);

        // Assert that the saved template is not null and has an ID assigned
        assertThat(savedTemplate).isNotNull();
        assertThat(savedTemplate.getId()).isNotNull();

        // Retrieve the template from the repository
        Optional<EmailTemplate> retrievedTemplate = emailTemplateRepository.findById(savedTemplate.getId());

        // Assert that the retrieved template is present and matches the saved template
        assertThat(retrievedTemplate).isPresent();
        assertThat(retrievedTemplate.get()).isEqualTo(savedTemplate);
    }

    @Test
    public void updateEmailTemplate() {
        // Create a mock email template
        EmailTemplate mockTemplate = EmailTemplateTestUtil.createMockEmailTemplateEntity();
        emailTemplateRepository.save(mockTemplate);

        // Modify the template's attributes
        mockTemplate.setName("Updated Template");
        mockTemplate.setSubject("Updated Subject");

        // Save the updated template
        emailTemplateRepository.save(mockTemplate);

        // Retrieve the template from the repository
        Optional<EmailTemplate> retrievedTemplate = emailTemplateRepository.findById(mockTemplate.getId());

        // Assert that the retrieved template is present and matches the updated attributes
        assertThat(retrievedTemplate).isPresent();
        assertThat(retrievedTemplate.get().getName()).isEqualTo("Updated Template");
        assertThat(retrievedTemplate.get().getSubject()).isEqualTo("Updated Subject");
    }

    @Test
    public void deleteEmailTemplate() {
        // Create a mock email template
        EmailTemplate mockTemplate = EmailTemplateTestUtil.createMockEmailTemplateEntity();
        emailTemplateRepository.save(mockTemplate);

        // Delete the template from the repository
        emailTemplateRepository.delete(mockTemplate);

        // Retrieve the template from the repository
        Optional<EmailTemplate> retrievedTemplate = emailTemplateRepository.findById(mockTemplate.getId());

        // Assert that the retrieved template is not present (deleted)
        assertThat(retrievedTemplate).isNotPresent();
    }

}
