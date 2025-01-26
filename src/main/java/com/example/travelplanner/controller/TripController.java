package com.example.travelplanner.controller;

import com.example.travelplanner.dto.*;
import com.example.travelplanner.service.TripService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<TripResponseDTO> createTrip(@Valid @RequestBody CreateTripDTO tripDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tripService.createTrip(tripDTO));
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<TripResponseDTO> getTrip(@PathVariable int tripId) {
        return ResponseEntity.ok(tripService.getTripById(tripId));
    }

    @GetMapping
    public ResponseEntity<List<TripResponseDTO>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }

    @GetMapping("/{tripId}/export")
    public ResponseEntity<byte[]> exportTripToPdf(@PathVariable int tripId) throws IOException {
        byte[] pdf = tripService.exportTripToPdf(tripId);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=trip.pdf")
                .body(pdf);
    }
}