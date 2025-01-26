package com.example.travelplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelplanner.models.*;
import com.example.travelplanner.repository.ItineraryRepository;

@Service
public class ItineraryService {
    @Autowired
    private ItineraryRepository itineraryRepository;

    public void addActivities(int tripId, List<Activity> activities) {
        Itinerary itinerary = itineraryRepository.findByTripId(tripId);
        itinerary.getActivities().addAll(activities);
        itineraryRepository.save(itinerary);
    }
}

