package com.example.iwemailsender.employee.domain;

import com.example.iwemailsender.infrastructure.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Column(name = "role", nullable = false, length = 50)
    private String role;
    @Column(name = "start_data", nullable = false)
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "phone_number", length = 11)
    private String phoneNumber;
    public Employee(String name, String email, String role, LocalDateTime startDate, LocalDateTime endDate, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
        this.phoneNumber = phoneNumber;
    }

    public Employee() {
    }
}
