package com.example.travelplanner.dto;

import java.util.List;

public class PackingListResponseDTO {
    private int id;
    private List<PackingItemResponseDTO> packingItems;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<PackingItemResponseDTO> getPackingItems() {
        return packingItems;
    }
    public void setPackingItems(List<PackingItemResponseDTO> packingItems) {
        this.packingItems = packingItems;
    }

    // Getters & Setters
    
}