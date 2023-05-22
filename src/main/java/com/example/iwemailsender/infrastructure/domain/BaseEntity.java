package com.example.iwemailsender.infrastructure.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on",insertable = true, updatable = false)
    private Date createdOn;

    @Column(name = "uuid", updatable = false)
    private String uuid;

    @PrePersist
    public void init(){
        uuid = UUID.randomUUID().toString();
        createdOn = Date.from(java.time.ZonedDateTime.now(ZoneOffset.UTC).toInstant());
    }
}
