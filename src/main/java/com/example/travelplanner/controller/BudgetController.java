package com.example.travelplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.travelplanner.service.BudgetService;
import com.example.travelplanner.models.*;

@Controller
@RequestMapping("/budgets")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    @PostMapping("/set")
    public String setTripBudget(@RequestParam int tripId, @RequestParam double totalBudget) {
        budgetService.setTripBudget(tripId, totalBudget);
        return "redirect:/trips/" + tripId;
    }

    @PostMapping("/add-expense")
    public String addExpenses(@RequestParam int tripId, @ModelAttribute List<Expense> expenses) {
        budgetService.addExpenses(tripId, expenses);
        return "redirect:/trips/" + tripId;
    }
}

