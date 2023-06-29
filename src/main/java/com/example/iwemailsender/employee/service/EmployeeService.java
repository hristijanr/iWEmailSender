package com.example.iwemailsender.employee.service;

import com.example.iwemailsender.employee.domain.Employee;
import com.example.iwemailsender.employee.dto.EmployeePojo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    public EmployeePojo findById(Long id);
    public EmployeePojo getByUuid(String uuid);

    public Employee findByUuid(String uuid);

    public List<EmployeePojo> getAll();

    public EmployeePojo createEmployee(EmployeePojo employeePojo);

    public EmployeePojo updateEmployee(String uuid, EmployeePojo employeePojo);

    public void removeEmployee(String uuid);

}
