package com.example.travelplanner.dto;

import jakarta.validation.constraints.*;

public class ExpenseDTO {
    @NotBlank(message = "Category is required")
    private String expenseCategory;
    
    @Positive(message = "Amount must be positive")
    private double amount;
    
    private String remarks; // Optional field

    // Getters & Setters
}
