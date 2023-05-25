package com.example.iwemailsender.employee.service;

import com.example.iwemailsender.employee.domain.Employee;
import com.example.iwemailsender.employee.dto.EmployeePojo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    public EmployeePojo findById(Long id);
    public EmployeePojo getByUuid(String uuid);

    public Employee findByUuid(String uuid);

    public List<EmployeePojo> getAll();

    public EmployeePojo createTemplate(EmployeePojo employeePojo);

    public EmployeePojo updateTemplate(String uuid, EmployeePojo employeePojo);

    public void removeEmployee(String uuid);

}
