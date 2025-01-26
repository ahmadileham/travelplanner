package com.example.travelplanner.service;

import com.example.travelplanner.models.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelplanner.repository.PackingItemRepository;
import com.example.travelplanner.repository.PackingListRepository;

@Service
public class PackingListService {
    @Autowired
    private PackingListRepository packingListRepository;
    
    @Autowired
    private PackingItemRepository packingItemRepository;

    public void addPackingItems(int tripId, List<PackingItem> items) {
        PackingList packingList = packingListRepository.findByTripId(tripId);
        items.forEach(item -> {
            item.setPackingList(packingList);
            item.setPacked(false);
        });
        packingList.getPackingItems().addAll(items);
        packingListRepository.save(packingList);
    }

    public List<PackingItem> loadFromPreviousTrip(int previousTripId) {
        return packingListRepository.findByTripId(previousTripId).getPackingItems();
    }

    public PackingItem getItemById(int itemId) {
        return packingItemRepository.findById(itemId)
            .orElseThrow(() -> new RuntimeException("Packing item not found"));
    }

    public void updateItem(int itemId, PackingItem updatedItem) {
        PackingItem item = getItemById(itemId);
        item.setItemName(updatedItem.getItemName());
        item.setQuantity(updatedItem.getQuantity());
        packingItemRepository.save(item);
    }

    public void deleteItem(int itemId) {
        packingItemRepository.deleteById(itemId);
    }

    public void togglePackedStatus(int itemId) {
        PackingItem item = getItemById(itemId);
        item.setPacked(!item.isPacked());
        packingItemRepository.save(item);
    }

    public PackingList getPackingListByTripId(int tripId) {
        return packingListRepository.findByTripId(tripId);
    }
}