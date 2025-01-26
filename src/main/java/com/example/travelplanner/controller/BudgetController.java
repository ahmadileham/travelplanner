package com.example.travelplanner.controller;

import com.example.travelplanner.dto.BudgetAnalysisDTO;
import com.example.travelplanner.dto.ExpenseDTO;
import com.example.travelplanner.dto.ExpenseResponseDTO;
import com.example.travelplanner.service.BudgetService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


import java.util.List;

@Controller
@RequestMapping("/trips/{tripId}/budget")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    // Show Budget Form
    @GetMapping
    public String showBudgetForm(@PathVariable int tripId, Model model) {
        BudgetAnalysisDTO analysis = budgetService.analyzeBudget(tripId);
        List<ExpenseResponseDTO> expenses = budgetService.getExpensesByBudget(tripId);
        model.addAttribute("tripId", tripId);
        model.addAttribute("analysis", analysis);
        model.addAttribute("expenses", expenses);
        model.addAttribute("expense", new ExpenseDTO());
        return "budget-form";
    }

    // Handle Add Expense Form Submission
    @PostMapping("/expenses")
    public String addExpense(@PathVariable int tripId, @ModelAttribute("expense") @Valid ExpenseDTO expenseDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "budget-form";
        }
        budgetService.addExpense(tripId, expenseDTO);
        return "redirect:/trips/" + tripId + "/budget";
    }

    // Show Expense Breakdown
    @GetMapping("/analysis")
    public String showExpenseBreakdown(@PathVariable int tripId, Model model) {
        BudgetAnalysisDTO analysis = budgetService.analyzeBudget(tripId);
        model.addAttribute("tripId", tripId);
        model.addAttribute("analysis", analysis);
        return "budget-analysis";
    }
}