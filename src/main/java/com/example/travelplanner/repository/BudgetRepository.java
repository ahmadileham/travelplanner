package com.example.travelplanner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelplanner.models.Budget;

public interface BudgetRepository extends JpaRepository<Budget,Integer>{

    Optional<Budget> findByTripId(int tripId);

}
