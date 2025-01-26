package com.example.travelplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelplanner.models.*;
import com.example.travelplanner.repository.ItineraryRepository;

import jakarta.transaction.Transactional;

@Service
public class ItineraryService {
    @Autowired
    private ItineraryRepository itineraryRepository;
    @Autowired
    private TripService tripService;

    @Transactional
    public void addActivities(int tripId, List<Activity> activities) {
        Trip trip = tripService.getTripDetails(tripId);
        Itinerary itinerary = itineraryRepository.findByTripId(tripId);
        
        // Validate and save activities
        for (Activity activity : activities) {
            if (activity.getActivityDate().before(trip.getStartDate()) || 
                activity.getActivityDate().after(trip.getEndDate())) {
                throw new IllegalArgumentException("Activity date must be within trip dates");
            }
            activity.setItinerary(itinerary);
        }
        
        itinerary.getActivities().addAll(activities);
        itineraryRepository.save(itinerary);
    }
}