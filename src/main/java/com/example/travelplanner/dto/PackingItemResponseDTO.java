package com.example.travelplanner.dto;

public class PackingItemResponseDTO {
    private int id;
    private String itemName;
    private int quantity;
    private boolean isPacked;
    private int packingListId;  // Reference to parent packing list
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public boolean isPacked() {
        return isPacked;
    }
    public void setPacked(boolean isPacked) {
        this.isPacked = isPacked;
    }
    public int getPackingListId() {
        return packingListId;
    }
    public void setPackingListId(int packingListId) {
        this.packingListId = packingListId;
    }

    // Getters & Setters
    
}