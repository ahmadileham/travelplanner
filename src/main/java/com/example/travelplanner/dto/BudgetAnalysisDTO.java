package com.example.travelplanner.dto;

import java.util.Map;

public record BudgetAnalysisDTO(
        double totalBudget,
        double totalExpenses,
        double remainingBudget,
        Map<String, Double> categoryTotals
) {}
