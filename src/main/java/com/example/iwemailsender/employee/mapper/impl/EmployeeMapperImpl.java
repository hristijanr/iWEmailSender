package com.example.iwemailsender.employee.mapper.impl;

import com.example.iwemailsender.employee.domain.Employee;
import com.example.iwemailsender.employee.dto.EmployeePojo;
import com.example.iwemailsender.employee.mapper.EmployeeMapper;
import com.example.iwemailsender.infrastructure.mapper.AbstractGeneralMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapperImpl extends AbstractGeneralMapper implements EmployeeMapper {

    @Autowired
    public EmployeeMapperImpl(ModelMapper modelMapper){
        super(modelMapper);
    }

    @Override
    public EmployeePojo entityToDto(Employee employee){
        return this.modelMapper.map(employee, EmployeePojo.class);
    }

    @Override
    public Employee dtoToEntity(EmployeePojo dto){
        return this.modelMapper.map(dto, Employee.class);
    }
    public void mapRequestedFieldForUpdate(Employee entity, EmployeePojo dto){
        entity.setName(dto.getName());
        entity.setRole(dto.getEmail());
        entity.setRole(dto.getRole());
    }
}
