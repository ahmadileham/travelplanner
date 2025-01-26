package com.example.travelplanner.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.travelplanner.service.PackingListService;
import com.example.travelplanner.models.*;

@Controller
@RequestMapping("/packing")
public class PackingListController {
    @Autowired
    private PackingListService packingListService;

    @PostMapping("/add-items")
    public String addPackingItems(@RequestParam int tripId, @ModelAttribute List<PackingItem> items) {
        packingListService.addPackingItems(tripId, items);
        return "redirect:/trips/" + tripId;
    }

    @GetMapping("/load-from-previous")
    public String loadPackingListFromPreviousTrip(@RequestParam int previousTripId, Model model) {
        model.addAttribute("items", packingListService.loadPackingListFromPreviousTrip(previousTripId));
        return "packinglist-form";
    }

    @GetMapping("/form")
    public String showPackingForm(@RequestParam int tripId, Model model) {
        // Initialize with one empty item
        List<PackingItem> items = new ArrayList<>();
        items.add(new PackingItem());
        
        model.addAttribute("tripId", tripId);
        model.addAttribute("packingListForm", new PackingListForm(items));
        return "packinglist-form";
    }

    @PostMapping("/save")
    public String savePackingItems(
        @RequestParam int tripId,
        @ModelAttribute("packingListForm") PackingListForm form
    ) {
        packingListService.addPackingItems(tripId, form.getItems());
        return "redirect:/trips/" + tripId;
    }

    // Helper class for form binding
    public static class PackingListForm {
        private List<PackingItem> items;

        public PackingListForm(List<PackingItem> items) {
            this.items = items;
        }

        // Getters and setters
        public List<PackingItem> getItems() { return items; }
        public void setItems(List<PackingItem> items) { this.items = items; }
    }
}
