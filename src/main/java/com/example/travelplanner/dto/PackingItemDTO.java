package com.example.travelplanner.dto;


public class PackingItemDTO {
    private String itemName;
    private int quantity;
    private boolean isPacked;

    
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

    

}
