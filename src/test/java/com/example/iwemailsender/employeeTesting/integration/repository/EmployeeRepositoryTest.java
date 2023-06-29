package com.example.iwemailsender.employeeTesting.integration.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.iwemailsender.employee.domain.Employee;
import com.example.iwemailsender.employee.repository.EmployeeRepository;
import com.example.iwemailsender.employeeTesting.utils.EmployeeTestUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(employeeRepository).isNotNull();
    }

    @Test
    public void getAllEmployees() {
        // Create a mock employee
        Employee mockEmployee = EmployeeTestUtil.createMockEmployeeEntity();
        employeeRepository.save(mockEmployee);

        // Retrieve all employees from the repository
        List<Employee> employees = employeeRepository.findAll();

        // Assert that the retrieved list is not empty
        assertThat(employees).isNotEmpty();

        // Assert that the mock employee is present in the retrieved list
        assertThat(employees).contains(mockEmployee);
    }

    @Test
    public void getEmployeeByUuid() {
        // Create a mock employee
        Employee mockEmployee = EmployeeTestUtil.createMockEmployeeEntity();
        employeeRepository.save(mockEmployee);

        // Retrieve the employee by UUID
        Optional<Employee> retrievedEmployee = employeeRepository.findByUuid(mockEmployee.getUuid());

        // Assert that the retrieved employee is present and matches the mock employee
        assertThat(retrievedEmployee).isPresent();
        assertThat(retrievedEmployee.get()).isEqualTo(mockEmployee);
    }

    @Test
    public void createNewEmployee() {
        // Create a new employee
        Employee newEmployee = EmployeeTestUtil.createMockEmployeeEntity();

        // Save the new employee to the repository
        Employee savedEmployee = employeeRepository.save(newEmployee);

        // Assert that the saved employee is not null and has an ID assigned
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isNotNull();

        // Retrieve the employee from the repository
        Optional<Employee> retrievedEmployee = employeeRepository.findById(savedEmployee.getId());

        // Assert that the retrieved employee is present and matches the saved employee
        assertThat(retrievedEmployee).isPresent();
        assertThat(retrievedEmployee.get()).isEqualTo(savedEmployee);
    }


    @Test
    public void updateEmployee() {
        // Create a mock employee
        Employee mockEmployee = EmployeeTestUtil.createMockEmployeeEntity();
        employeeRepository.save(mockEmployee);

        // Modify the employee's attributes
        mockEmployee.setName("Updated Name");
        mockEmployee.setEmail("updated-email@example.com");

        // Save the updated employee
        employeeRepository.save(mockEmployee);

        // Retrieve the employee from the repository
        Optional<Employee> retrievedEmployee = employeeRepository.findById(mockEmployee.getId());

        // Assert that the retrieved employee is present and matches the updated attributes
        assertThat(retrievedEmployee).isPresent();
        assertThat(retrievedEmployee.get().getName()).isEqualTo("Updated Name");
        assertThat(retrievedEmployee.get().getEmail()).isEqualTo("updated-email@example.com");
    }

    @Test
    public void deleteEmployee() {
        // Create a mock employee
        Employee mockEmployee = EmployeeTestUtil.createMockEmployeeEntity();
        employeeRepository.save(mockEmployee);

        // Delete the employee from the repository
        employeeRepository.delete(mockEmployee);

        // Retrieve the employee from the repository
        Optional<Employee> retrievedEmployee = employeeRepository.findById(mockEmployee.getId());

        // Assert that the retrieved employee is not present (deleted)
        assertThat(retrievedEmployee).isNotPresent();
    }


}
