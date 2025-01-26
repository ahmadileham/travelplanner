package com.example.travelplanner.controller;

import com.example.travelplanner.dto.*;
import com.example.travelplanner.service.TripService;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/trips")
public class TripController {
    private static final Logger logger = LoggerFactory.getLogger(TripController.class); // Add Logger
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    // Home Page: List all trips
    @GetMapping
    public String listTrips(Model model) {
        model.addAttribute("trips", tripService.getAllTrips());
        return "trip-list";
    }

    // Trip Details Page
    @GetMapping("/{id}")
    public String viewTrip(@PathVariable int id, Model model) {
        TripResponseDTO trip = tripService.getTripById(id);
        model.addAttribute("trip", trip);
        return "trip-details";
    }

    // Show Create Trip Form
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("trip", new CreateTripDTO());
        return "trip-create";
    }

    // Handle Create Trip Form Submission
    @PostMapping
    public String createTrip(@ModelAttribute("trip") @Valid CreateTripDTO tripDTO, BindingResult result) {
        logger.info("Handling trip creation form submission for trip: {}", tripDTO);
        if (result.hasErrors()) {
            return "trip-create";
        }
        tripService.createTrip(tripDTO);
        return "redirect:/trips";
    }
}