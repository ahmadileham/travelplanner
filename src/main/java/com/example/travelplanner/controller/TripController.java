package com.example.travelplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        
        boolean isBudgetEmpty = trip.getBudget().getTotalBudget() == 0;
        boolean isExpenseEmpty = trip.getBudget().getExpenses().isEmpty();
        boolean isPackingListEmpty = trip.getPackingList().getPackingItems().isEmpty();
        boolean isItineraryEmpty = trip.getItinerary().getActivities().isEmpty();
        
        model.addAttribute("isBudgetEmpty", isBudgetEmpty);
        model.addAttribute("isExpenseEmpty", isExpenseEmpty);
        model.addAttribute("isPackingListEmpty", isPackingListEmpty);
        model.addAttribute("isItineraryEmpty", isItineraryEmpty);
        
        return "trip-details";
    }

    @PostMapping("/add")
    public String createTrip(@ModelAttribute Trip trip, Model model, RedirectAttributes redirectAttributes) {
        try {
            tripService.createTrip(trip);
            redirectAttributes.addFlashAttribute("successMessage", "Trip created successfully!");
            return "redirect:/trips";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An unexpected error occurred.");
        }
        model.addAttribute("trip", new Trip());
        return "add-trip";
    }

    @GetMapping("/add-form")
    public String showAddTripForm(Model model) {
        model.addAttribute("trip", new Trip());
        return "add-trip";
    }

    @GetMapping("/edit-form/{id}")
    public String showEditTripForm(@PathVariable int id, Model model) {
        model.addAttribute("trip", tripService.getTripDetails(id));
        return "edit-trip";
    }

    @PostMapping("/delete/{id}")
    public String deleteTrip(@PathVariable int id) {
        tripService.deleteTrip(id);
        return "redirect:/trips";
    }

    @PostMapping("/update/{id}")
    public String updateTrip(@PathVariable int id, @ModelAttribute Trip trip, Model model, RedirectAttributes redirectAttributes) {
        try {
            tripService.updateTrip(id, trip);
            redirectAttributes.addFlashAttribute("successMessage", "Trip created successfully!");
            return "redirect:/trips/" + id;
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An unexpected error occurred.");
        }
        model.addAttribute("trip", tripService.getTripDetails(id));
        return "edit-trip";
    }

}

