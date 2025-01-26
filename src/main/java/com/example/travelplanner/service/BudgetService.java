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

    public void addExpenses(int tripId, List<Expense> expenses) {
        Budget budget = budgetRepository.findByTripId(tripId);
        budget.getExpenses().addAll(expenses);
        budget.setRemainingBudget(
                budget.getRemainingBudget() - expenses.stream().mapToDouble(Expense::getAmount).sum()
        );
        budgetRepository.save(budget);
    }

    public void setTripBudget(int tripId, double totalBudget) {
        Budget budget = budgetRepository.findByTripId(tripId);
        budget.setTotalBudget(totalBudget);
        budget.setRemainingBudget(totalBudget);
        budgetRepository.save(budget);
    }
}

