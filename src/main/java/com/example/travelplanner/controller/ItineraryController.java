package com.example.travelplanner.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.travelplanner.dto.ActivityDTO;
import com.example.travelplanner.models.Activity;
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

    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @GetMapping("/edit/{id}")
    public String editActivityForm(@PathVariable int id, Model model) {
        model.addAttribute("activity", itineraryService.getActivityById(id));
        return "edit-activity";
    }

    @PostMapping("/update/{id}")
    public String updateActivity(@PathVariable int id, @ModelAttribute Activity activity, Model model) {

        try {
            itineraryService.updateActivity(activity);
        return "redirect:/trips/" + itineraryService.getActivityById(id)
            .getItinerary().getTrip().getId();
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An unexpected error occurred.");
        }

        return "edit-activity";
    }

    @PostMapping("/delete/{id}")
    public String deleteActivity(@PathVariable int id) {
        int tripId = itineraryService.getActivityById(id)
            .getItinerary().getTrip().getId();
        itineraryService.deleteActivity(id);
        return "redirect:/trips/" + tripId;
    }

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
        Model model
    ) {

        try {
            itineraryService.addActivities(tripId, activityDTO.getActivities());
            return "redirect:/trips/" + tripId;
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An unexpected error occurred.");
        }
        return "itinerary-form";
    }
}