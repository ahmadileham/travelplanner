package com.example.travelplanner.controller;

import com.example.travelplanner.dto.ActivityDTO;
import com.example.travelplanner.dto.ActivityResponseDTO;
import com.example.travelplanner.service.ItineraryService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryController {

    private final ItineraryService itineraryService;

    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @PostMapping("/{itineraryId}/activities")
    public ResponseEntity<ActivityResponseDTO> addActivity(
            @PathVariable int itineraryId,
            @Valid @RequestBody ActivityDTO activityDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(itineraryService.addActivity(itineraryId, activityDTO));
    }

    @GetMapping("/{itineraryId}/activities")
    public ResponseEntity<List<ActivityResponseDTO>> getActivities(@PathVariable int itineraryId) {
        return ResponseEntity.ok(itineraryService.getActivitiesByItinerary(itineraryId));
    }
}