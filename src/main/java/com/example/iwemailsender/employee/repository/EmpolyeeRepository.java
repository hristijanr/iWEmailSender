package com.example.iwemailsender.employee.repository;


import com.example.iwemailsender.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpolyeeRepository extends JpaRepository<Employee, Long> {
    public Optional<Employee> findByUuid(String uuid);

}
