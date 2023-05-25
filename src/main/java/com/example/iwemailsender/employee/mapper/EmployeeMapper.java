package com.example.iwemailsender.employee.mapper;
import com.example.iwemailsender.employee.domain.Employee;
import com.example.iwemailsender.employee.dto.EmployeePojo;
import com.example.iwemailsender.infrastructure.mapper.GeneralMapper;

public interface EmployeeMapper extends GeneralMapper<EmployeePojo, Employee> {
    public void mapRequestedFieldForUpdate(Employee entity, EmployeePojo dto);

}
