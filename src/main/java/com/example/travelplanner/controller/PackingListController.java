package com.example.travelplanner.controller;

import com.example.travelplanner.dto.PackingItemDTO;
import com.example.travelplanner.dto.PackingItemResponseDTO;
import com.example.travelplanner.service.PackingListService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
@RequestMapping("/trips/{tripId}/packing")
public class PackingListController {

    private final PackingListService packingListService;

    public PackingListController(PackingListService packingListService) {
        this.packingListService = packingListService;
    }

    // Show Packing List Form
    @GetMapping
    public String showPackingListForm(@PathVariable int tripId, Model model) {
        List<PackingItemResponseDTO> items = packingListService.getItemsByPackingList(tripId);
        model.addAttribute("tripId", tripId);
        model.addAttribute("items", items);
        model.addAttribute("item", new PackingItemDTO());
        return "packing-list";
    }

    // Handle Add Packing Item Form Submission
    @PostMapping("/items")
    public String addItem(@PathVariable int tripId, @ModelAttribute("item") @Valid PackingItemDTO itemDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "packing-list";
        }
        packingListService.addItem(tripId, itemDTO);
        return "redirect:/trips/" + tripId + "/packing";
    }
}