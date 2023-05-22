package com.example.iwemailsender.email_template.repository;

import com.example.iwemailsender.email_template.domain.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {
    public Optional<EmailTemplate> findByUuid(String uuid);
}
