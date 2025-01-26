package com.example.travelplanner.models;
import java.util.Comparator;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    public static Comparator<Activity> compareByDateTime = (a1, a2) -> {
        LocalDateTime dt1 = LocalDateTime.of(
            a1.getActivityDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
            a1.getActivityTime()
        );
        LocalDateTime dt2 = LocalDateTime.of(
            a2.getActivityDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
            a2.getActivityTime()
        );
        return dt1.compareTo(dt2);
    };

    
}
