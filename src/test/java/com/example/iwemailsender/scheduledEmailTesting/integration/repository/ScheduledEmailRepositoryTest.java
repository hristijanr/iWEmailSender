package com.example.iwemailsender.scheduledEmailTesting.integration.repository;

import com.example.iwemailsender.emailTemplateTesting.utils.EmailTemplateTestUtil;
import com.example.iwemailsender.email_template.domain.EmailTemplate;
import com.example.iwemailsender.email_template.repository.EmailTemplateRepository;
import com.example.iwemailsender.employee.domain.Employee;
import com.example.iwemailsender.employee.repository.EmployeeRepository;
import com.example.iwemailsender.scheduledEmailTesting.utils.ScheduledEmailTestUtil;
import com.example.iwemailsender.scheduled_email.domain.ScheduledEmail;
import com.example.iwemailsender.scheduled_email.repository.ScheduledEmailRepository;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.Assert;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ScheduledEmailRepositoryTest {

    @Autowired
    private ScheduledEmailRepository scheduledEmailRepository;

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(scheduledEmailRepository).isNotNull();
        assertThat(emailTemplateRepository).isNotNull();
        assertThat(employeeRepository).isNotNull();
    }

    @Test
    public void saveScheduledEmail() {
        // Create a mock email template
        EmailTemplate mockTemplate = EmailTemplateTestUtil.createMockEmailTemplateEntity();
        emailTemplateRepository.save(mockTemplate);

        // Create a new scheduled email
        ScheduledEmail scheduledEmail = new ScheduledEmail();
        scheduledEmail.setRecipientEmail("example@example.com");
        scheduledEmail.setSendDate(LocalDateTime.now());
        scheduledEmail.setEmailTemplate(mockTemplate);
        //ENTER THE ID FOR THE CORRESPOND EMPLOYEE
        // Retrieve an employee from the repository and set it as the value for the employee field
        Employee employee = employeeRepository.findById(63L).orElseThrow(); // ADD the appropriate employee ID
        scheduledEmail.setEmployee(employee);

        // Save the scheduled email to the repository
        ScheduledEmail savedScheduledEmail = scheduledEmailRepository.save(scheduledEmail);

        // Rest of the test assertions...
    }

    @Test
    public void findScheduledEmailByUuid() {
        // Create a mock email template
        EmailTemplate mockTemplate = EmailTemplateTestUtil.createMockEmailTemplateEntity();
        emailTemplateRepository.save(mockTemplate);

        // Create a new scheduled email
        ScheduledEmail scheduledEmail = new ScheduledEmail();
        scheduledEmail.setRecipientEmail("example@example.com");
        scheduledEmail.setSendDate(LocalDateTime.now());
        scheduledEmail.setEmailTemplate(mockTemplate);

        // Retrieve an employee from the repository and set it as the value for the employee field
        Employee employee = employeeRepository.findById(63L).orElseThrow(); // ADD the appropriate employee ID
        scheduledEmail.setEmployee(employee);

        // Save the scheduled email to the repository
        ScheduledEmail savedScheduledEmail = scheduledEmailRepository.save(scheduledEmail);

        // Retrieve the scheduled email by UUID
        Optional<ScheduledEmail> retrievedScheduledEmail = scheduledEmailRepository.findByUuid(savedScheduledEmail.getUuid().toString());

        // Assert that the retrieved scheduled email is present and matches the saved scheduled email
        assertThat(retrievedScheduledEmail).isPresent();
        assertThat(retrievedScheduledEmail.get()).isEqualTo(savedScheduledEmail);
    }


    @Test
    public void findAllScheduledEmailsWithTemplates() {
        // Create a mock email template
        EmailTemplate mockTemplate = EmailTemplateTestUtil.createMockEmailTemplateEntity();
        emailTemplateRepository.save(mockTemplate);

        // Create a new scheduled email
        ScheduledEmail scheduledEmail = new ScheduledEmail();
        scheduledEmail.setRecipientEmail("example@example.com");
        scheduledEmail.setSendDate(LocalDateTime.now());
        scheduledEmail.setEmailTemplate(mockTemplate);

        // Retrieve an existing employee from the repository
        Employee employee = employeeRepository.findById(63L).orElseThrow(); // ADD the appropriate employee ID
        scheduledEmail.setEmployee(employee);

        // Save the scheduled email to the repository
        scheduledEmailRepository.save(scheduledEmail);

        // Retrieve all scheduled emails with email templates
        List<ScheduledEmail> scheduledEmails = scheduledEmailRepository.findAllWithTemplates();

        // Assert that the retrieved list is not empty
        assertThat(scheduledEmails).isNotEmpty();

        // Assert that the mock email template is present in the retrieved list
        assertThat(scheduledEmails.get(0).getEmailTemplate().getTemplate()).isEqualTo(mockTemplate.getTemplate());
        assertThat(scheduledEmails.get(0).getEmailTemplate().getSubject()).isEqualTo(mockTemplate.getSubject());
        assertThat(scheduledEmails.get(0).getEmailTemplate().getName()).isEqualTo(mockTemplate.getName());
    }


    @Test
    public void deleteScheduledEmail() {
        Employee employee = new Employee();
        // Set properties for the employee as needed
        employee.setName("John Doe");
        employee.setEmail("john@example.com");
        employee.setRole("Default Role"); // Set a default role value here
        employee.setStartDate(LocalDateTime.now()); // Set a default start date value here

        EmailTemplate emailTemplate = new EmailTemplate();
        // Set properties for the emailTemplate as needed
        emailTemplate.setTemplate("Template 1");
        emailTemplate.setSubject("Subject 1");
        emailTemplate.setName("Body 1");

        // Save the employee first
        employeeRepository.save(employee);

        // Save the email template
        emailTemplateRepository.save(emailTemplate);

        ScheduledEmail scheduledEmail = ScheduledEmailTestUtil.createMockScheduledEmailEntity(employee, emailTemplate);

        // Save the scheduled email
        scheduledEmailRepository.save(scheduledEmail);

        // Fetch the scheduled email from the repository
        ScheduledEmail fetchedScheduledEmail = scheduledEmailRepository.findById(scheduledEmail.getId()).orElse(null);
        assertNotNull(fetchedScheduledEmail);

        // Delete the scheduled email
        scheduledEmailRepository.delete(scheduledEmail);

        // Fetch the scheduled email again to verify deletion
        fetchedScheduledEmail = scheduledEmailRepository.findById(scheduledEmail.getId()).orElse(null);
        assertNull(fetchedScheduledEmail);
    }


}
