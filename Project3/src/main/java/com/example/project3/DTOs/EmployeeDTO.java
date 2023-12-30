package com.example.project3.DTOs;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {
    @NotNull(message = "user id not null")
    private Integer user_id;
    @NotNull(message = "position cannot be null")
    private String position;
    @NotNull(message = "salary cannot be null")
    @Positive(message = "salary must be positive")
    private double salary;
}
