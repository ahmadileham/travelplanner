package com.example.travelplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelplanner.models.Trip;

public interface TripRepository extends JpaRepository<Trip,Integer>{

}
