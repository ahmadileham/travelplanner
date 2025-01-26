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
        PackingList packingList = packingListRepository.findByTripId(tripId);
        packingList.getPackingItems().addAll(items);
        packingListRepository.save(packingList);
    }

    public List<PackingItem> loadPackingListFromPreviousTrip(int previousTripId) {
        return packingListRepository.findByTripId(previousTripId).getPackingItems();
    }
}
