package com.example.travelplanner.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.*;

public class CreateTripDTO {
    @NotBlank(message = "Trip name is required")
    private String tripName;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotBlank(message = "Trip destination is required")
    private String destination;

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String name) {
        this.tripName = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    
}
