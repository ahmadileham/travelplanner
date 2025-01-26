package com.example.travelplanner.dto;

import java.time.LocalTime;
import java.util.Date;

public class ActivityResponseDTO {
    private int id;
    private String activityName;
    private String destination;
    private Date activityDate;
    private LocalTime activityTime;
    private int itineraryId;  // Only include ID to avoid nested relationships
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getActivityName() {
        return activityName;
    }
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public Date getActivityDate() {
        return activityDate;
    }
    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
    public LocalTime getActivityTime() {
        return activityTime;
    }
    public void setActivityTime(LocalTime activityTime) {
        this.activityTime = activityTime;
    }
    public int getItineraryId() {
        return itineraryId;
    }
    public void setItineraryId(int itineraryId) {
        this.itineraryId = itineraryId;
    }

    // Getters & Setters
    
}