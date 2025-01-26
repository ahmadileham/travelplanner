package com.example.travelplanner.dto;

import java.util.Date;

public class TripResponseDTO {
    private int id;
    private String tripName;
    private String destination;
    private Date startDate;
    private Date endDate;
    private ItineraryResponseDTO itinerary;
    private PackingListResponseDTO packingList;
    private BudgetResponseDTO budget;
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
    public ItineraryResponseDTO getItinerary() {
        return itinerary;
    }
    public void setItinerary(ItineraryResponseDTO itinerary) {
        this.itinerary = itinerary;
    }
    public PackingListResponseDTO getPackingList() {
        return packingList;
    }
    public void setPackingList(PackingListResponseDTO packingList) {
        this.packingList = packingList;
    }
    public BudgetResponseDTO getBudget() {
        return budget;
    }
    public void setBudget(BudgetResponseDTO budget) {
        this.budget = budget;
    }

    
}
