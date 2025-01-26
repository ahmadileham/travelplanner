package com.example.travelplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.travelplanner.models.Trip;
import com.example.travelplanner.service.TripService;

@Controller
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripService tripService;

    @GetMapping
    public String listTrips(Model model) {
        model.addAttribute("trips", tripService.getAllTrips());
        return "home";
    }

    @GetMapping("/{id}")
    public String getTripDetails(@PathVariable int id, Model model) {
        Trip trip = tripService.getTripDetails(id);
        model.addAttribute("trip", trip);
        
        boolean isBudgetEmpty = trip.getBudget().getExpenses().isEmpty() && trip.getBudget().getTotalBudget() == 0;
        boolean isPackingListEmpty = trip.getPackingList().getPackingItems().isEmpty();
        boolean isItineraryEmpty = trip.getItinerary().getActivities().isEmpty();
        
        model.addAttribute("isBudgetEmpty", isBudgetEmpty);
        model.addAttribute("isPackingListEmpty", isPackingListEmpty);
        model.addAttribute("isItineraryEmpty", isItineraryEmpty);
        
        return "trip-details";
    }

    @PostMapping("/add")
    public String createTrip(@ModelAttribute Trip trip) {
        tripService.createTrip(trip);
        return "redirect:/trips";
    }

    @GetMapping("/add-form")
    public String showAddTripForm(Model model) {
        model.addAttribute("trip", new Trip());
        return "add-trip";
    }

}

