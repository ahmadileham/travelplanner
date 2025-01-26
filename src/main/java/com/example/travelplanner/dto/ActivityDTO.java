package com.example.travelplanner.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.time.LocalTime;

public class ActivityDTO {
    @NotBlank(message = "Activity name is required")
    private String activityName;
    
    @NotBlank(message = "Destination is required")
    private String destination;
    
    @NotNull(message = "Date is required")
    private Date activityDate;
    
    @NotNull(message = "Time is required")
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
