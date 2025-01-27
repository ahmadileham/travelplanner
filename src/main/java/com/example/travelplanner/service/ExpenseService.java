package com.example.travelplanner.service;


import org.springframework.stereotype.Service;

import com.example.travelplanner.models.Budget;
import com.example.travelplanner.models.Expense;
import com.example.travelplanner.repository.BudgetRepository;
import com.example.travelplanner.repository.ExpenseRepository;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;

    
    public ExpenseService(ExpenseRepository expenseRepository, BudgetRepository budgetRepository) {
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
    }

    public Expense getExpenseById(int id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    public void updateExpense(int expenseId, Expense updatedExpense) {
        if (updatedExpense.getAmount() < 0) {
            throw new IllegalArgumentException("Expense amount cannot be less than 0 for: " + updatedExpense.getExpenseCategory());
        }
        Expense existingExpense = getExpenseById(expenseId);
        Budget budget = existingExpense.getBudget();
        
        // Adjust remaining budget
        budget.setRemainingBudget(budget.getRemainingBudget() + existingExpense.getAmount());
        
        existingExpense.setExpenseCategory(updatedExpense.getExpenseCategory());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setRemarks(updatedExpense.getRemarks());
        
        // Subtract new amount
        budget.setRemainingBudget(budget.getRemainingBudget() - updatedExpense.getAmount());
        
        expenseRepository.save(existingExpense);
        budgetRepository.save(budget);
    }

    public void deleteExpense(int expenseId) {
        Expense expense = getExpenseById(expenseId);
        Budget budget = expense.getBudget();
        budget.setRemainingBudget(budget.getRemainingBudget() + expense.getAmount());
        
        expenseRepository.delete(expense);
        budgetRepository.save(budget);
    }
}
