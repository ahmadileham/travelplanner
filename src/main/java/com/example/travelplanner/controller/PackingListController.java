package com.example.travelplanner.controller;

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
}
