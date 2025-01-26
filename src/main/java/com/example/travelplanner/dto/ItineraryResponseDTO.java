package com.example.travelplanner.dto;

import java.util.List;

public class ItineraryResponseDTO {
    private int id;
    private List<ActivityResponseDTO> activities;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<ActivityResponseDTO> getActivities() {
        return activities;
    }
    public void setActivities(List<ActivityResponseDTO> activities) {
        this.activities = activities;
    }

}
