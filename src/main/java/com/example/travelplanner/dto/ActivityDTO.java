package com.example.travelplanner.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.travelplanner.models.Activity;
import com.example.travelplanner.models.Itinerary;

public class ActivityDTO {
    private List<Activity> activities;

    public ActivityDTO() {
        this.activities = new ArrayList<>();
        activities.add(new Activity()); // Initialize with one empty activity
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

     public static class ActivityForm {
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date activityDate;

        private String destination;
        private String activityName;
        private LocalTime activityTime;
        private Itinerary itinerary;

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
    
        public Itinerary getItinerary() {
            return itinerary;
        }
    
        public void setItinerary(Itinerary itinerary) {
            this.itinerary = itinerary;
        }
    }
    
    
}
