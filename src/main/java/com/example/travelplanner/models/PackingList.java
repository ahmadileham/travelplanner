package com.example.travelplanner.models;

import java.util.List;

import jakarta.persistence.*;

public class PackingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip trip;

    @OneToMany(mappedBy = "packingitem", cascade = CascadeType.ALL)
    private List<PackingItem> packingItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public List<PackingItem> getPackingItems() {
        return packingItems;
    }

    public void setPackingItems(List<PackingItem> packingItems) {
        this.packingItems = packingItems;
    }

    
}
