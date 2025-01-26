package com.example.travelplanner.dto;
import java.util.Date;
import java.time.LocalTime;

public class ActivityDTO {
    private String activityName;
    
    private String destination;
    
    private Date activityDate;
    
    private LocalTime activityTime;

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

    // Getters & Setters
}
