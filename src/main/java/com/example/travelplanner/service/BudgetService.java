package com.example.travelplanner.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.travelplanner.dto.BudgetAnalysisDTO;
import com.example.travelplanner.dto.ExpenseDTO;
import com.example.travelplanner.dto.ExpenseResponseDTO;
import com.example.travelplanner.exception.ResourceNotFoundException;
import com.example.travelplanner.models.Budget;
import com.example.travelplanner.models.Expense;
import com.example.travelplanner.repository.BudgetRepository;
import com.example.travelplanner.repository.ExpenseRepository;
import org.modelmapper.ModelMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BudgetService {
    private BudgetRepository budgetRepository;
    private ExpenseRepository expenseRepository;
    private ModelMapper modelMapper;

    public BudgetService(BudgetRepository budgetRepository,
                        ExpenseRepository expenseRepository,
                        ModelMapper modelMapper) {
        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
        this.modelMapper = modelMapper;
    }

    // Add Expense
    public ExpenseResponseDTO addExpense(int budgetId, ExpenseDTO expenseDTO) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found"));
        
        Expense expense = modelMapper.map(expenseDTO, Expense.class);
        expense.setBudget(budget);
        Expense savedExpense = expenseRepository.save(expense);
        
        // Update budget totals
        updateBudgetCalculations(budget);
        return modelMapper.map(savedExpense, ExpenseResponseDTO.class);
    }

    // Set Budget (using budgetId)
    public void setBudget(int budgetId, BigDecimal amount, String currency) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found"));
        
        budget.setTotalBudget(amount.doubleValue());
        budgetRepository.save(budget);
    }

    // Get Expenses
    public List<ExpenseResponseDTO> getExpensesByBudget(int budgetId) {
        List<Expense> expenses = expenseRepository.findByBudgetId(budgetId);
        return expenses.stream()
                .map(expense -> modelMapper.map(expense, ExpenseResponseDTO.class))
                .toList();
    }

    private void updateBudgetCalculations(Budget budget) {
        double totalExpenses = budget.getExpenses().stream()
                .mapToDouble(Expense::getAmount)
                .sum();
        budget.setRemainingBudget(budget.getTotalBudget() - totalExpenses);
        budgetRepository.save(budget);
    }

    // 6. Analyze budget
    public BudgetAnalysisDTO analyzeBudget(int tripId) {
        Budget budget = budgetRepository.findByTripId(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found"));
        
        double totalExpenses = budget.getExpenses().stream()
                .mapToDouble(Expense::getAmount)
                .sum();
        
        // Analyze budget by category
        Map<String, Double> categoryTotals = new HashMap<>();
        for (Expense expense : budget.getExpenses()) {
            categoryTotals.merge(expense.getExpenseCategory(), expense.getAmount(), Double::sum);
        }

        return new BudgetAnalysisDTO(
                budget.getTotalBudget(),
                totalExpenses,
                budget.getTotalBudget() - totalExpenses,
                categoryTotals // Add category totals to the DTO
        );
    }


    // 8. Set budget
    public void setTripBudget(int tripId, BigDecimal amount, String currency) {
        Budget budget = budgetRepository.findByTripId(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found"));
        
        budget.setTotalBudget(amount.doubleValue());
        budgetRepository.save(budget);
    }
}
