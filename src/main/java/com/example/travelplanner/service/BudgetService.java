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
        if (totalBudget < 0) {
            throw new IllegalArgumentException("Total budget cannot be less than 0.");
        }

        Budget budget = budgetRepository.findByTripId(tripId);
        budget.setTotalBudget(totalBudget);

        double totalExpenses = budget.getExpenses().stream()
            .mapToDouble(Expense::getAmount)
            .sum();
        budget.setRemainingBudget(totalBudget - totalExpenses);
        budgetRepository.save(budget);
    }

    public void addExpenses(int tripId, List<Expense> expenses) {
        Budget budget = budgetRepository.findByTripId(tripId);
        expenses.forEach(expense -> {
            if (expense.getAmount() < 0) {
                throw new IllegalArgumentException("Expense amount cannot be less than 0 for: " + expense.getExpenseCategory());
            }
            expense.setBudget(budget);
            budget.getExpenses().add(expense);
            budget.setRemainingBudget(budget.getRemainingBudget() - expense.getAmount());
        });
        budgetRepository.save(budget);
    }
}

