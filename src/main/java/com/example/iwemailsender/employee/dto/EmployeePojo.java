package com.example.iwemailsender.employee.dto;

import com.example.iwemailsender.infrastructure.pojo.BasePojo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class EmployeePojo extends BasePojo {
    @NotNull(message="name value must not be null")
    private String name;
    @NotNull(message="email value must not be null")
    private String email;
    @NotNull(message="role value must not be null")
    private String role;

    private Date modifiedOn;
}
