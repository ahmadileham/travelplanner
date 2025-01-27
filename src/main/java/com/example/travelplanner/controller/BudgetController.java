package com.example.travelplanner.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.travelplanner.service.BudgetService;
import com.example.travelplanner.dto.ExpenseDTO;
import com.example.travelplanner.models.*;

@Controller
@RequestMapping("/budget")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    @GetMapping("/form")
    public String showBudgetForm(@RequestParam int tripId, Model model) {
        model.addAttribute("tripId", tripId);
        return "budget-form";
    }

    @GetMapping("/expense-form")
    public String showExpenseForm(@RequestParam int tripId, Model model) {
        model.addAttribute("tripId", tripId);
        model.addAttribute("expenses", new ArrayList<Expense>());
        return "expense-form";
    }

    @PostMapping("/set")
    public String setTripBudget(
        @RequestParam int tripId,
        @RequestParam double totalBudget, Model model
    ) {
        
        try {
            budgetService.setTripBudget(tripId, totalBudget);
            model.addAttribute("successMessage", "Budget set successfully!");
            return "redirect:/trips/" + tripId;
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An unexpected error occurred.");
        }
        model.addAttribute("tripId", tripId);
        return "budget-form";
    }

    @PostMapping("/add-expense")
    public String addExpenses(
        @RequestParam int tripId,
        @ModelAttribute("expenses") ExpenseDTO expenses, Model model, RedirectAttributes redirectAttributes
    ) {
        
        try {
            budgetService.addExpenses(tripId, expenses.getExpenses());
            redirectAttributes.addFlashAttribute("successMessage", "Expenses added successfully!");
            return "redirect:/trips/" + tripId;
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An unexpected error occurred.");
        }
        model.addAttribute("tripId", tripId);
        model.addAttribute("expenses", new ArrayList<Expense>());
        return "expense-form";
    }
}
