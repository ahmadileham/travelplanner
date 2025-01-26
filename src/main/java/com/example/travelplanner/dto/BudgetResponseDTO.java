package com.example.travelplanner.dto;

import java.util.List;

public class BudgetResponseDTO {
    private int id;
    private double totalBudget;
    private double remainingBudget;
    private List<ExpenseResponseDTO> expenses;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getTotalBudget() {
        return totalBudget;
    }
    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }
    public double getRemainingBudget() {
        return remainingBudget;
    }
    public void setRemainingBudget(double remainingBudget) {
        this.remainingBudget = remainingBudget;
    }
    public List<ExpenseResponseDTO> getExpenses() {
        return expenses;
    }
    public void setExpenses(List<ExpenseResponseDTO> expenses) {
        this.expenses = expenses;
    }

    
}
