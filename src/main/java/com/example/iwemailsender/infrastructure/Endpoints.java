package com.example.iwemailsender.infrastructure;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Endpoints {
    public static final String BASE = "/api";
    public static final String EmailTemplate = BASE + "email_templates/";

    public static final String Employee = BASE + "employees/";

    public static final String ScheduledEmail = BASE + "scheduled_emails/";
}
