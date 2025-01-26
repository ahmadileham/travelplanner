package com.example.travelplanner.service;

import com.example.travelplanner.repository.BudgetRepository;
import com.example.travelplanner.models.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    public void setTripBudget(int tripId, double totalBudget) {
        Budget budget = budgetRepository.findByTripId(tripId);
        budget.setTotalBudget(totalBudget);
        // Reset remaining budget when setting new total
        budget.setRemainingBudget(totalBudget);
        budgetRepository.save(budget);
    }

    public void addExpenses(int tripId, List<Expense> expenses) {
        Budget budget = budgetRepository.findByTripId(tripId);
        expenses.forEach(expense -> {
            expense.setBudget(budget);
            budget.getExpenses().add(expense);
            budget.setRemainingBudget(budget.getRemainingBudget() - expense.getAmount());
        });
        budgetRepository.save(budget);
    }
}

