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
}
