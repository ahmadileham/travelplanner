package com.example.travelplanner.dto;

import java.util.List;

import com.example.travelplanner.models.Expense;

public class ExpenseDTO {
    private List<Expense> expenses;

    

    public ExpenseDTO(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    
}
