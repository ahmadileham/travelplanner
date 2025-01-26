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
        @RequestParam double totalBudget
    ) {
        budgetService.setTripBudget(tripId, totalBudget);
        return "redirect:/trips/" + tripId;
    }

    @PostMapping("/add-expense")
    public String addExpenses(
        @RequestParam int tripId,
        @ModelAttribute("expenses") ExpenseDTO expenses
    ) {
        budgetService.addExpenses(tripId, expenses.getExpenses());
        return "redirect:/trips/" + tripId;
    }
}
