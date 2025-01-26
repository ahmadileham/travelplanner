package com.example.travelplanner.dto;

import jakarta.validation.constraints.*;

public class ExpenseDTO {
    @NotBlank(message = "Category is required")
    private String expenseCategory;
    
    @Positive(message = "Amount must be positive")
    private double amount;
    
    private String remarks; // Optional field

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    // Getters & Setters
    
}
