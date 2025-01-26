package com.example.travelplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.travelplanner.models.Expense;
import com.example.travelplanner.service.ExpenseService;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("expense", expenseService.getExpenseById(id));
        return "edit-expense";
    }

    @PostMapping("/update/{id}")
    public String updateExpense(@PathVariable int id, @ModelAttribute Expense expense) {
        expenseService.updateExpense(id, expense);
        return "redirect:/trips/" + expenseService.getExpenseById(id).getBudget().getTrip().getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteExpense(@PathVariable int id) {
        int tripId = expenseService.getExpenseById(id).getBudget().getTrip().getId();
        expenseService.deleteExpense(id);
        return "redirect:/trips/" + tripId;
    }
}