package com.example.iwemailsender.employee.service.impl;

import com.example.iwemailsender.email_template.domain.EmailTemplate;
import com.example.iwemailsender.email_template.dto.EmailTemplatePojo;
import com.example.iwemailsender.employee.domain.Employee;
import com.example.iwemailsender.employee.dto.EmployeePojo;
import com.example.iwemailsender.employee.mapper.EmployeeMapper;
import com.example.iwemailsender.employee.service.EmployeeService;
import com.example.iwemailsender.employee.repository.EmpolyeeRepository;
import com.example.iwemailsender.infrastructure.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmpolyeeRepository empolyeeRepository;
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public EmployeePojo findById(Long id){
        Employee employee = empolyeeRepository.findById(id).orElseThrow(()->{
           log.error("Employee with id {} is not found", id);
            return new ResourceNotFoundException("Employee not found");
        });
        return employeeMapper.entityToDto(employee);
    }

    @Override
    public EmployeePojo getByUuid(String uuid){
        log.debug("Execute getByUuid with parameter {}", uuid);
        return employeeMapper.entityToDto(findByUuid(uuid));
    }

    @Override
    public Employee findByUuid(String uuid){
        log.debug("Execute findByUuid with parameter {}", uuid);
        return empolyeeRepository.findByUuid(uuid).orElseThrow(()->{
            log.error("Error: Resource employee with uuid {} is not found", uuid);
            return new ResourceNotFoundException("Employee not found");
        });
    }

    @Override
    public List<EmployeePojo> getAll(){
        return employeeMapper.mapList(empolyeeRepository.findAll(), EmployeePojo.class);
    }

    @Override
    public EmployeePojo createTemplate(EmployeePojo employeePojo){
        log.debug("Execute create employee with parameters ", employeePojo);
        Employee createEmployee = employeeMapper.dtoToEntity(employeePojo);
        Employee savingEmployee = empolyeeRepository.save(createEmployee);

        return employeeMapper.entityToDto(savingEmployee);
    }

    @Override
    public EmployeePojo updateTemplate(String uuid, EmployeePojo employeePojo){
            log.debug("Execute employee with parameter {}", employeePojo);
            Employee targetEmployee = findByUuid(uuid);
            employeeMapper.mapRequestedFieldForUpdate(targetEmployee, employeePojo);

            return employeeMapper.entityToDto(empolyeeRepository.saveAndFlush(targetEmployee));
    }

    @Override
    public void removeEmployee(String uuid){
        Employee targetEmployee = findByUuid(uuid);
        empolyeeRepository.delete(targetEmployee);
    }
}
