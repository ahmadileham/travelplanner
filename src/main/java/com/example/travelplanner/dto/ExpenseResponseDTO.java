package com.example.travelplanner.dto;

public class ExpenseResponseDTO {
    private int id;
    private String expenseCategory;
    private double amount;
    private String remarks;
    private int budgetId;  // Reference to parent budget

    // Getters & Setters
}