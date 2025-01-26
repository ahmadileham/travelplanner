package com.example.travelplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelplanner.models.Trip;
import com.example.travelplanner.repository.TripRepository;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip getTripDetails(int id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
    }

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }
}

