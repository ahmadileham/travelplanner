package com.example.travelplanner.dto;

import jakarta.validation.constraints.*;

public class PackingItemDTO {
    @NotBlank(message = "Item name is required")
    private String itemName;
    
    @Positive(message = "Quantity must be positive")
    private int quantity;

}
