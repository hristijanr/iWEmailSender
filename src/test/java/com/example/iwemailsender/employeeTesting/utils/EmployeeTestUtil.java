package com.example.iwemailsender.employeeTesting.utils;

import com.example.iwemailsender.employee.domain.Employee;
import com.example.iwemailsender.employee.dto.EmployeePojo;

import java.time.LocalDateTime;
import java.util.Date;

public class EmployeeTestUtil {
    public static Employee createMockEmployeeEntity(){
        Employee mock = new Employee();
        mock.setCreatedOn(new Date());
        mock.setUuid("Mock UUID");
        mock.setName("Mock Name");
        mock.setEmail("Mock Email");
        mock.setRole("Mock Role");
        mock.setStartDate(LocalDateTime.now().minusYears(2));
        mock.setEndDate(null);
        return mock;
    }

    public static EmployeePojo createMockEmployeePojo(String name, String email, String role){
        EmployeePojo mock = new EmployeePojo();
        mock.setName(name);
        mock.setEmail(email);
        mock.setRole(role);
        return mock;
    }
}
