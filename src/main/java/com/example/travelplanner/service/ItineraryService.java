package com.example.travelplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelplanner.models.*;
import com.example.travelplanner.repository.ActivityRepository;
import com.example.travelplanner.repository.ItineraryRepository;

import jakarta.transaction.Transactional;

@Service
public class ItineraryService {
    @Autowired
    private ItineraryRepository itineraryRepository;
    @Autowired
    private TripService tripService;
    @Autowired
    private ActivityRepository activityRepository;


    public ItineraryService(ItineraryRepository itineraryRepository,
                           ActivityRepository activityRepository,
                           TripService tripService) {
        this.itineraryRepository = itineraryRepository;
        this.activityRepository = activityRepository;
        this.tripService = tripService;
    }

    @Transactional
    public Activity getActivityById(int activityId) {
        return activityRepository.findById(activityId)
            .orElseThrow(() -> new RuntimeException("Activity not found"));
    }

    @Transactional
    public void updateActivity(Activity updatedActivity) {
        Activity existingActivity = getActivityById(updatedActivity.getId());
        
        // Validate trip dates
        Trip trip = existingActivity.getItinerary().getTrip();
        if (updatedActivity.getActivityDate().before(trip.getStartDate()) 
            || updatedActivity.getActivityDate().after(trip.getEndDate())) {
            throw new IllegalArgumentException("Activity date must be within trip dates");
        }
        
        existingActivity.setActivityName(updatedActivity.getActivityName());
        existingActivity.setDestination(updatedActivity.getDestination());
        existingActivity.setActivityDate(updatedActivity.getActivityDate());
        existingActivity.setActivityTime(updatedActivity.getActivityTime());
        
        activityRepository.save(existingActivity);
    }

    @Transactional
    public void deleteActivity(int activityId) {
        Activity activity = getActivityById(activityId);
        activityRepository.delete(activity);
    }


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