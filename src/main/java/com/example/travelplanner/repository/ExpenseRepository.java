package com.example.travelplanner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelplanner.models.Expense;

public interface ExpenseRepository extends JpaRepository<Expense,Integer>{

    List<Expense> findByBudgetId(int budgetId);

}
