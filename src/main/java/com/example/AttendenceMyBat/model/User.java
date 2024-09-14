package com.example.AttendenceMyBat.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int emp_id;
    @NotNull(message = "Name cannot be empty !!")
    @NotEmpty
    @Size(min = 4)
    private String name;

    @NotNull(message = "Email cannot be empty !!")
    @Email(message = "Enter a valid email !!")
    private String email;

    @NotEmpty
    @NotNull(message = "Role cannot be empty !!")
    private String role;

    private boolean emp_status;

    public User(int emp_id, String name, String email, String role) {
        this.emp_id=emp_id;
        this.name=name;
        this.email=email;
        this.role=role;

    }
}
