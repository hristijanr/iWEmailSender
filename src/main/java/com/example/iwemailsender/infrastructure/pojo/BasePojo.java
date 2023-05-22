package com.example.iwemailsender.infrastructure.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BasePojo {
    private String uuid;
    private Date createdOn;
}
