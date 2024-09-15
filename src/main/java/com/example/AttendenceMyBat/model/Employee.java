package com.example.AttendenceMyBat.model;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Timestamp;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    private int emp_id;

    @NotNull(message = "Name cannot be empty !!")
    @NotEmpty(message = "Name cannot be empty !!")
    @Size(min = 4, message = "Name must be at least 4 characters long")
    private String name;

    @NotNull(message = "Email cannot be empty !!")
    @NotEmpty(message = "Email cannot be empty !!")
    private String email;

    @NotNull(message = "Gender cannot be null")
    private String gender;

    @NotNull(message = "Date of joining cannot be null")
    private Date date_of_joining;

    @NotNull(message = "Department cannot be null")
    private String department;

    private String phone_number;

    private String position;

    @NotNull(message = "Salary cannot be null")
    private BigDecimal salary;

    @NotNull(message = "Role cannot be null")
    private String role;

    @NotNull(message = "Employment status cannot be null")
    private Boolean emp_status;

    private Timestamp last_updated;
}


