package com.example.travelplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.travelplanner.models.Activity;
import com.example.travelplanner.service.ItineraryService;

@Controller
@RequestMapping("/itineraries")
public class ItineraryController {
    @Autowired
    private ItineraryService itineraryService;

    @PostMapping("/add")
    public String addActivities(@RequestParam int tripId, @ModelAttribute List<Activity> activities) {
        itineraryService.addActivities(tripId, activities);
        return "redirect:/trips/" + tripId;
    }
}
