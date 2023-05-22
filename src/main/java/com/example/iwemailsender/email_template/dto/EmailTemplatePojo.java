package com.example.iwemailsender.email_template.dto;

import com.example.iwemailsender.infrastructure.pojo.BasePojo;
import lombok.Data;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Data
public class EmailTemplatePojo extends BasePojo {
    @NotNull(message="name value must not be null")
    private String name;
    @NotNull(message="subject value must not be null")
    private String subject;
    @NotNull(message="template value must not be null")
    private String template;

    private Date modifiedOn;
}
