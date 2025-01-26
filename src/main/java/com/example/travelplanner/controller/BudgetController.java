package com.example.travelplanner.controller;

import com.example.travelplanner.dto.BudgetAnalysisDTO;
import com.example.travelplanner.dto.ExpenseDTO;
import com.example.travelplanner.dto.ExpenseResponseDTO;
import com.example.travelplanner.service.BudgetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping("/{budgetId}/expenses")
    public ResponseEntity<ExpenseResponseDTO> addExpense(
            @PathVariable int budgetId,
            @Valid @RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(budgetService.addExpense(budgetId, expenseDTO));
    }

    @GetMapping("/{budgetId}/analysis")
    public ResponseEntity<BudgetAnalysisDTO> analyzeBudget(@PathVariable int budgetId) {
        return ResponseEntity.ok(budgetService.analyzeBudget(budgetId));
    }


    @PutMapping("/{budgetId}/set-budget")
    public ResponseEntity<Void> setBudget(
            @PathVariable int budgetId,
            @RequestParam BigDecimal amount,
            @RequestParam String currency) {
        budgetService.setBudget(budgetId, amount, currency);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{budgetId}/expenses")
    public ResponseEntity<List<ExpenseResponseDTO>> getExpenses(@PathVariable int budgetId) {
        return ResponseEntity.ok(budgetService.getExpensesByBudget(budgetId));
    }
}