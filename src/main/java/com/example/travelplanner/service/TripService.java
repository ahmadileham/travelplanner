package com.example.travelplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelplanner.models.Budget;
import com.example.travelplanner.models.Itinerary;
import com.example.travelplanner.models.PackingList;
import com.example.travelplanner.models.Trip;
import com.example.travelplanner.repository.BudgetRepository;
import com.example.travelplanner.repository.ItineraryRepository;
import com.example.travelplanner.repository.PackingListRepository;
import com.example.travelplanner.repository.TripRepository;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private ItineraryRepository itineraryRepository;
    @Autowired
    private PackingListRepository packingListRepository;

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip getTripDetails(int id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
    }

    public Trip createTrip(Trip trip) {
        Trip savedTrip = tripRepository.save(trip);

        // Initialize Budget
        Budget budget = new Budget();
        budget.setTrip(savedTrip);
        budget.setTotalBudget(0);
        budget.setRemainingBudget(0);
        budgetRepository.save(budget);

        // Initialize Itinerary
        Itinerary itinerary = new Itinerary();
        itinerary.setTrip(savedTrip);
        itineraryRepository.save(itinerary);

        // Initialize PackingList
        PackingList packingList = new PackingList();
        packingList.setTrip(savedTrip);
        packingListRepository.save(packingList);

        return savedTrip;
    }
}

