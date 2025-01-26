package com.example.travelplanner.models;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String tripName;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private Date startDate; 
    
    @Column(nullable = false)
    private Date endDate;

    @OneToOne(mappedBy = "trip", cascade = CascadeType.ALL)
    private Itinerary itinerary;

    @OneToOne(mappedBy = "trip", cascade = CascadeType.ALL)
    private Budget budget;

    @OneToOne(mappedBy = "trip", cascade = CascadeType.ALL)
    private PackingList packingList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public PackingList getPackingList() {
        return packingList;
    }

    public void setPackingList(PackingList packingList) {
        this.packingList = packingList;
    }

}
