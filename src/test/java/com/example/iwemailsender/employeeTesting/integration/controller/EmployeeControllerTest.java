package com.example.iwemailsender.employeeTesting.integration.controller;

import com.example.iwemailsender.employee.api.EmployeeController;
import com.example.iwemailsender.employee.dto.EmployeePojo;
import com.example.iwemailsender.employee.service.EmployeeService;
import com.example.iwemailsender.infrastructure.Endpoints;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EmployeeController employeeController = new EmployeeController(employeeService);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getByUuid_ReturnsEmployee() throws Exception {
        // Arrange
        String uuid = "employee-uuid";
        EmployeePojo mockEmployee = new EmployeePojo();
        mockEmployee.setName("John Doe");
        mockEmployee.setEmail("johndoe@example.com");
        mockEmployee.setRole("Manager");

        when(employeeService.getByUuid(uuid)).thenReturn(mockEmployee);

        // Act and Assert
        mockMvc.perform(get(Endpoints.Employee + "/" + uuid))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"))
                .andExpect(jsonPath("$.role").value("Manager"));

        verify(employeeService, times(1)).getByUuid(uuid);
    }

    @Test
    public void getAll_ReturnsListOfEmployees() throws Exception {
        // Arrange
        EmployeePojo mockEmployee1 = new EmployeePojo();
        mockEmployee1.setName("John Doe");
        mockEmployee1.setEmail("johndoe@example.com");
        mockEmployee1.setRole("Manager");

        EmployeePojo mockEmployee2 = new EmployeePojo();
        mockEmployee2.setName("Jane Smith");
        mockEmployee2.setEmail("janesmith@example.com");
        mockEmployee2.setRole("Employee");

        List<EmployeePojo> mockEmployees = Arrays.asList(mockEmployee1, mockEmployee2);

        when(employeeService.getAll()).thenReturn(mockEmployees);

        // Act and Assert
        mockMvc.perform(get(Endpoints.Employee))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("johndoe@example.com"))
                .andExpect(jsonPath("$[0].role").value("Manager"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(jsonPath("$[1].email").value("janesmith@example.com"))
                .andExpect(jsonPath("$[1].role").value("Employee"));

        verify(employeeService, times(1)).getAll();
    }

    @Test
    public void createEmployee_ReturnsCreatedEmployee() throws Exception {
        // Arrange
        EmployeePojo employeePojo = new EmployeePojo();
        employeePojo.setName("John Doe");
        employeePojo.setEmail("johndoe@example.com");
        employeePojo.setRole("Manager");

        EmployeePojo createdEmployee = new EmployeePojo();
        createdEmployee.setUuid("employee-uuid");
        createdEmployee.setName("John Doe");
        createdEmployee.setEmail("johndoe@example.com");
        createdEmployee.setRole("Manager");

        when(employeeService.createEmployee(any(EmployeePojo.class))).thenReturn(createdEmployee);

        // Act and Assert
        mockMvc.perform(post(Endpoints.Employee)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employeePojo)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.uuid").value("employee-uuid"))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"))
                .andExpect(jsonPath("$.role").value("Manager"));

        verify(employeeService, times(1)).createEmployee(any(EmployeePojo.class));
    }

    @Test
    public void updateEmployee_ReturnsUpdatedEmployee() throws Exception {
        // Arrange
        String uuid = "employee-uuid";
        EmployeePojo employeePojo = new EmployeePojo();
        employeePojo.setName("John Doe");
        employeePojo.setEmail("johndoe@example.com");
        employeePojo.setRole("Manager");

        EmployeePojo updatedEmployee = new EmployeePojo();
        updatedEmployee.setUuid(uuid);
        updatedEmployee.setName("John Doe");
        updatedEmployee.setEmail("johndoe@example.com");
        updatedEmployee.setRole("Employee");

        when(employeeService.updateEmployee(eq(uuid), any(EmployeePojo.class))).thenReturn(updatedEmployee);

        // Act and Assert
        mockMvc.perform(put(Endpoints.Employee + "/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employeePojo)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.uuid").value(uuid))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"))
                .andExpect(jsonPath("$.role").value("Employee"));

        verify(employeeService, times(1)).updateEmployee(eq(uuid), any(EmployeePojo.class));
    }

    @Test
    public void removeEmployee_ReturnsNoContent() throws Exception {
        // Arrange
        String uuid = "employee-uuid";

        // Act and Assert
        mockMvc.perform(delete(Endpoints.Employee + "/" + uuid))
                .andExpect(status().isNoContent());

        verify(employeeService, times(1)).removeEmployee(uuid);
    }

    private String asJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
