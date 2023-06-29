package com.example.iwemailsender.scheduled_email.repository;

import com.example.iwemailsender.scheduled_email.domain.ScheduledEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScheduledEmailRepository extends JpaRepository<ScheduledEmail, Long> {
    Optional<ScheduledEmail> findByUuid(String uuid);

    @Query("SELECT se FROM ScheduledEmail se JOIN FETCH se.emailTemplate JOIN FETCH se.employee")
    List<ScheduledEmail> findAllWithTemplates();
}
