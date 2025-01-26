package com.example.travelplanner.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.travelplanner.service.PackingListService;
import com.example.travelplanner.models.*;
import com.example.travelplanner.repository.PackingItemRepository;

@Controller
@RequestMapping("/packing")
public class PackingListController {
    @Autowired
    private PackingListService packingListService;
    @Autowired
    private PackingItemRepository packingItemRepository;

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

    // Edit Form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        PackingItem item = packingItemRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid item ID"));
        model.addAttribute("item", item);
        return "edit-packing-item";
    }

    // Update Item
    @PostMapping("/update/{id}")
    public String updateItem(@PathVariable int id, @ModelAttribute("item") PackingItem updatedItem) {
        PackingItem item = packingItemRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid item ID"));
        
        item.setItemName(updatedItem.getItemName());
        item.setQuantity(updatedItem.getQuantity());
        packingItemRepository.save(item);
        
        return "redirect:/trips/" + item.getPackingList().getTrip().getId();
    }

    // Delete Item
    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable int id) {
        PackingItem item = packingItemRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid item ID"));
        int tripId = item.getPackingList().getTrip().getId();
        packingItemRepository.delete(item);
        return "redirect:/trips/" + tripId;
    }

    // Toggle Packed Status
    @PostMapping("/toggle-packed/{id}")
    public String togglePackedStatus(@PathVariable int id) {
        PackingItem item = packingItemRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid item ID"));
        item.setPacked(!item.isPacked());
        packingItemRepository.save(item);
        return "redirect:/trips/" + item.getPackingList().getTrip().getId();
    }
}
