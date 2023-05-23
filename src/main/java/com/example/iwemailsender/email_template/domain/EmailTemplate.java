package com.example.iwemailsender.email_template.domain;

import com.example.iwemailsender.infrastructure.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "email_templates")
public class EmailTemplate extends BaseEntity {


    @Column(name = "name",nullable = false, length = 100)
    private String name;

    @Column(name = "subject",nullable = false, length = 200)
    private String subject;

    @Column(name = "template",nullable = false, columnDefinition = "TEXT")
    private String template;

    public EmailTemplate(String name, String subject, String template) {
        this.name = name;
        this.subject = subject;
        this.template = template;
    }

    public EmailTemplate() {
    }
}
