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

@Controller
@RequestMapping("/packing")
public class PackingListController {
    @Autowired
    private PackingListService packingListService;

    // Form handling
    @GetMapping("/form")
    public String showPackingForm(@RequestParam int tripId, Model model) {
        // Initialize with mutable ArrayList
        List<PackingItem> initialItems = new ArrayList<>();
        initialItems.add(new PackingItem());
        
        model.addAttribute("tripId", tripId);
        model.addAttribute("form", new PackingListForm(initialItems));
        return "packinglist-form";
    }

    @PostMapping("/save")
    public String savePackingItems(
        @RequestParam int tripId,
        @ModelAttribute("form") PackingListForm form
    ) {
        packingListService.addPackingItems(tripId, form.getItems());
        return "redirect:/trips/" + tripId;
    }

    // Item operations
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("item", packingListService.getItemById(id));
        return "edit-packing-item";
    }

    @PostMapping("/update/{id}")
    public String updateItem(@PathVariable int id, @ModelAttribute PackingItem item) {
        packingListService.updateItem(id, item);
        return "redirect:/trips/" + packingListService.getItemById(id).getPackingList().getTrip().getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable int id) {
        int tripId = packingListService.getItemById(id).getPackingList().getTrip().getId();
        packingListService.deleteItem(id);
        return "redirect:/trips/" + tripId;
    }

    @PostMapping("/toggle-packed/{id}")
    public String togglePackedStatus(@PathVariable int id) {
        int tripId = packingListService.getItemById(id).getPackingList().getTrip().getId();
        packingListService.togglePackedStatus(id);
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
