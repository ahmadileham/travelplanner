package com.example.travelplanner.dto;

public class ExpenseResponseDTO {
    private int id;
    private String expenseCategory;
    private double amount;
    private String remarks;
    private int budgetId;  // Reference to parent budget
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
    public int getBudgetId() {
        return budgetId;
    }
    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    // Getters & Setters
    
}