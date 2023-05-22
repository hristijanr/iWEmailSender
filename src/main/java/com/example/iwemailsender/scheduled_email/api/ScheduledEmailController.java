package com.example.iwemailsender.scheduled_email.api;

import com.example.iwemailsender.infrastructure.Endpoints;
import com.example.iwemailsender.scheduled_email.dto.ScheduledEmailPojo;
import com.example.iwemailsender.scheduled_email.service.ScheduledEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Endpoints.ScheduledEmail)
public class ScheduledEmailController {
    @Autowired
    ScheduledEmailService scheduledEmailService;

    @GetMapping("/{uuid}")
    public ScheduledEmailPojo getByUuid(@PathVariable(value = "uuid") String uuid){
        return scheduledEmailService.getByUuid(uuid);
    }

    @GetMapping
    public List<ScheduledEmailPojo> getAll(){
        return scheduledEmailService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduledEmailPojo createScheduledEmail(@Valid @RequestBody ScheduledEmailPojo scheduledEmailPojo){
        return scheduledEmailService.createTemplate(scheduledEmailPojo);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ScheduledEmailPojo updateScheduledEmail(@PathVariable(value = "uuid") String uuid, @Valid @RequestBody ScheduledEmailPojo scheduledEmailPojo){
        return scheduledEmailService.updateTemplate(uuid,scheduledEmailPojo);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteScheduledEmail(@PathVariable(value = "uuid") String uuid){
        scheduledEmailService.removeEmployee(uuid);
    }
}
