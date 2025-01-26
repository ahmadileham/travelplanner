package com.example.travelplanner.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.travelplanner.dto.ActivityDTO;
import com.example.travelplanner.models.Trip;
import com.example.travelplanner.service.ItineraryService;
import com.example.travelplanner.service.TripService;

@Controller
@RequestMapping("/itinerary")
public class ItineraryController {
    @Autowired
    private ItineraryService itineraryService;
    @Autowired
    private TripService tripService;

    @GetMapping("/form")
    public String showItineraryForm(
        @RequestParam int tripId,
        Model model
    ) {
        Trip trip = tripService.getTripDetails(tripId);
        model.addAttribute("tripId", tripId);
        model.addAttribute("activityDTO", new ActivityDTO());
        model.addAttribute("tripStart", trip.getStartDate());
        model.addAttribute("tripEnd", trip.getEndDate());
        return "itinerary-form";
    }

    @PostMapping("/save")
    public String saveItinerary(
        @RequestParam int tripId,
        @ModelAttribute("activityDTO") ActivityDTO activityDTO,
        RedirectAttributes redirectAttributes
    ) {
        try {
            itineraryService.addActivities(tripId, activityDTO.getActivities());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/itinerary/form?tripId=" + tripId;
        }
        return "redirect:/trips/" + tripId;
    }
}