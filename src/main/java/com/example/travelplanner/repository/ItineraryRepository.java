package com.example.travelplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelplanner.models.Itinerary;

public interface ItineraryRepository extends JpaRepository<Itinerary,Integer>{

    Itinerary findByTripId(int tripId);

}
