package com.example.iwemailsender.email_template.api;

import com.example.iwemailsender.email_template.dto.EmailTemplatePojo;
import com.example.iwemailsender.email_template.service.EmailTemplateService;
import com.example.iwemailsender.employee.service.EmployeeService;
import com.example.iwemailsender.infrastructure.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Endpoints.EmailTemplate)
public class EmailTemplateController {
    private final EmailTemplateService emailTemplateService;

    @Autowired
    public EmailTemplateController(EmailTemplateService emailTemplateService) {
        this.emailTemplateService = emailTemplateService;
    }

    @GetMapping("/{uuid}")
    public EmailTemplatePojo getByUuid(@PathVariable(value = "uuid") String uuid){
        return emailTemplateService.getByUuid(uuid);
    }

    @GetMapping
    public List<EmailTemplatePojo> getAll(){

        return emailTemplateService.getAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public EmailTemplatePojo createEmailTemplate(@Valid @RequestBody EmailTemplatePojo emailTemplatePojo){
        return emailTemplateService.createTemplate(emailTemplatePojo);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    public EmailTemplatePojo updateEmailTemplate(@PathVariable(value = "uuid") String uuid, @Valid @RequestBody EmailTemplatePojo emailTemplatePojo){
        return emailTemplateService.updateTemplate(uuid, emailTemplatePojo);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeEmailTemplate(@PathVariable(value = "uuid") String uuid){
        emailTemplateService.removeEmailTemplate(uuid);
    }
}
