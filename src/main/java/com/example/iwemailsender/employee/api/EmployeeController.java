package com.example.iwemailsender.employee.api;

import com.example.iwemailsender.employee.dto.EmployeePojo;
import com.example.iwemailsender.employee.service.EmployeeService;
import com.example.iwemailsender.infrastructure.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Endpoints.Employee)
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/{uuid}")
    public EmployeePojo getByUuid(@PathVariable(value = "uuid")String uuid) {
        return employeeService.getByUuid(uuid);

    }

    @GetMapping
    public List<EmployeePojo> getAll(){

        return employeeService.getAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public EmployeePojo createEmployee(@Valid @RequestBody EmployeePojo employeePojo){
        return employeeService.createTemplate(employeePojo);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeePojo updateEmployee(@PathVariable(value = "uuid") String uuid, @Valid @RequestBody EmployeePojo employeePojo){
        return employeeService.updateTemplate(uuid,employeePojo);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeEmployee(@PathVariable(value = "uuid") String uuid){
        employeeService.removeEmployee(uuid);
    }
}
