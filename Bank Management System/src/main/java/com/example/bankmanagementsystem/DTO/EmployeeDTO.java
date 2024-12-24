package com.example.bankmanagementsystem.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    @NotEmpty(message = "Please enter a username")
    @Size(min = 4, max = 10, message = "username must be between 4 and 10 in length")
    private String username;

    @NotEmpty(message = "Please enter a password")
    @Size(min = 6, message = "Password must be more than 6 characters in length")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$", message = "Password must contain at least one digit [0-9].Password must contain at least one lowercase Latin character [a-z].Password must contain at least one uppercase Latin character [A-Z].Password must contain at least one special character like ! @ # & ( ).Password must contain a length of at least 8 characters and a maximum of 20 characters.")
    private String password;

    @NotEmpty(message = "Please enter a name")
    @Size(min = 2, max = 20, message = "name must be between 2 and 20 characters in length")
    private String name;

    @NotEmpty(message = "Please enter an email")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotEmpty(message = "Please enter a position")
    private String position;

    @NotNull(message = "Please enter a salary")
    @PositiveOrZero(message = "Salary must not be a negative number")
    @Min(value = 4000, message = "Salary can't be less than 4000 per regulations")
    private Double salary;
}
