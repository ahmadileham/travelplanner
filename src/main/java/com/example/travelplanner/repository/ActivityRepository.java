package com.example.travelplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelplanner.models.Activity;

public interface ActivityRepository extends JpaRepository<Activity,Integer>{

}
