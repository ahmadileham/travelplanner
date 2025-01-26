package com.example.travelplanner.controller;

import com.example.travelplanner.dto.ActivityDTO;
import com.example.travelplanner.dto.ActivityResponseDTO;
import com.example.travelplanner.service.ItineraryService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/trips/{tripId}/itinerary")
public class ItineraryController {

    private final ItineraryService itineraryService;

    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    // Show Itinerary Form
    @GetMapping
    public String showItineraryForm(@PathVariable int tripId, Model model) {
        List<ActivityResponseDTO> activities = itineraryService.getActivitiesByItinerary(tripId);
        model.addAttribute("tripId", tripId);
        model.addAttribute("activities", activities);
        model.addAttribute("activity", new ActivityDTO());
        return "itinerary-form";
    }

    // Handle Add Activity Form Submission
    @PostMapping("/activities")
    public String addActivity(@PathVariable int tripId, @ModelAttribute("activity") @Valid ActivityDTO activityDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "itinerary-form";
        }
        itineraryService.addActivity(tripId, activityDTO);
        return "redirect:/trips/" + tripId + "/itinerary";
    }
}