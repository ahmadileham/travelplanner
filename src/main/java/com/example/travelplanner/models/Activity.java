package com.example.travelplanner.models;
import java.util.Date;
import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String activityName;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private Date activityDate; 
    
    @Column(nullable = false)
    private LocalTime activityTime;

    @ManyToOne
    @JoinColumn(name = "itinerary_id", referencedColumnName = "id")
    private Itinerary itinerary;

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

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    
}
