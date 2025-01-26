package com.example.travelplanner.dto;

import java.time.LocalDate;
import java.util.List;

public class TripDTO {
    private int id;
    private String tripName;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalBudget;
    private double remainingBudget;
    private List<ActivityDTO> activities;
    private List<PackingItemDTO> packingItems;
    private List<ExpenseDTO> expenses;
    
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
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public double getTotalBudget() {
        return totalBudget;
    }
    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }
    public double getRemainingBudget() {
        return remainingBudget;
    }
    public void setRemainingBudget(double remainingBudget) {
        this.remainingBudget = remainingBudget;
    }
    public List<ActivityDTO> getActivities() {
        return activities;
    }
    public void setActivities(List<ActivityDTO> activities) {
        this.activities = activities;
    }
    public List<PackingItemDTO> getPackingItems() {
        return packingItems;
    }
    public void setPackingItems(List<PackingItemDTO> packingItems) {
        this.packingItems = packingItems;
    }
    public List<ExpenseDTO> getExpenses() {
        return expenses;
    }
    public void setExpenses(List<ExpenseDTO> expenses) {
        this.expenses = expenses;
    }

    
}
