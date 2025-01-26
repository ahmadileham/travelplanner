package com.example.travelplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelplanner.models.PackingItem;

public interface PackingItemRepository extends JpaRepository<PackingItem, Integer>{

}
