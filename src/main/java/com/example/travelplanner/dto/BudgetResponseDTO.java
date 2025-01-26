package com.example.travelplanner.dto;

import java.util.List;

public class BudgetResponseDTO {
    private int id;
    private double totalBudget;
    private double remainingBudget;
    private List<ExpenseResponseDTO> expenses;
}
