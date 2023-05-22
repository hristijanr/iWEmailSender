package com.example.iwemailsender.scheduled_email.repository;

import com.example.iwemailsender.scheduled_email.domain.ScheduledEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduledEmailRepository extends JpaRepository<ScheduledEmail, Long> {
    public Optional<ScheduledEmail> findByUuid(String uuid);

}
