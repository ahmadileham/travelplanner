package com.example.travelplanner.service;

import com.example.travelplanner.models.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelplanner.repository.PackingListRepository;

@Service
public class PackingListService {
    @Autowired
    private PackingListRepository packingListRepository;

    public void addPackingItems(int tripId, List<PackingItem> items) {
        // Get the existing packing list for the trip
        PackingList packingList = packingListRepository.findByTripId(tripId);

        // Associate each item with the packing list
        items.forEach(item -> {
            item.setPackingList(packingList); 
            item.setPacked(false); 
        });

        // Save the updated list
        packingList.getPackingItems().addAll(items);
        packingListRepository.save(packingList);
    }

    public List<PackingItem> loadPackingListFromPreviousTrip(int previousTripId) {
        return packingListRepository.findByTripId(previousTripId).getPackingItems();
    }
}
