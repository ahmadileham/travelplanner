package com.example.travelplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelplanner.models.Budget;

public interface BudgetRepository extends JpaRepository<Budget,Integer>{

    Budget findByTripId(int tripId);

}
