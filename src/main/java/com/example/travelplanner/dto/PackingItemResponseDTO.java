package com.example.travelplanner.dto;

public class PackingItemResponseDTO {
    private int id;
    private String itemName;
    private int quantity;
    private boolean isPacked;
    private int packingListId;  // Reference to parent packing list

    // Getters & Setters
}