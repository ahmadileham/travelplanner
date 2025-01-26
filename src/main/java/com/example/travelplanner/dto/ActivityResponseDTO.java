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

    // Getters & Setters
}