package com.example.travelplanner.models;

import jakarta.persistence.*;


@Entity
@Table(name = "packingitem")
public class PackingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private boolean isPacked;

    @ManyToOne
    @JoinColumn(name = "packinglist_id", referencedColumnName = "id")
    private PackingList packingList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isPacked() {
        return isPacked;
    }

    public void setPacked(boolean isPacked) {
        this.isPacked = isPacked;
    }

    public PackingList getPackingList() {
        return packingList;
    }

    public void setPackingList(PackingList packingList) {
        this.packingList = packingList;
    }

    
}
