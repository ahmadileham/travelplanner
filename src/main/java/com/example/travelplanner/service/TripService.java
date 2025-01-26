package com.example.travelplanner.service;

import java.io.IOException;
import java.util.List;
import java.io.ByteArrayOutputStream;


import org.springframework.stereotype.Service;

import com.example.travelplanner.dto.CreateTripDTO;
import com.example.travelplanner.dto.TripResponseDTO;
import com.example.travelplanner.exception.ResourceNotFoundException;
import com.example.travelplanner.models.Budget;
import com.example.travelplanner.models.Itinerary;
import com.example.travelplanner.models.PackingList;
import com.example.travelplanner.models.Trip;
import com.example.travelplanner.repository.TripRepository;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.modelmapper.ModelMapper;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class TripService {
    private final TripRepository tripRepository;
    private final PackingListService packingListService;
    private final ModelMapper modelMapper;

    public TripService(TripRepository tripRepository, PackingListService packingListService, ModelMapper modelMapper) {
        this.tripRepository = tripRepository;
        this.packingListService = packingListService;
        this.modelMapper = modelMapper;
    }

    // 1. Add Trip
    public TripResponseDTO createTrip(CreateTripDTO tripDTO) {
        Trip trip = modelMapper.map(tripDTO, Trip.class);
        
        // Initialize related entities
        trip.setItinerary(new Itinerary());
        trip.setPackingList(new PackingList());
        trip.setBudget(new Budget());
        
        Trip savedTrip = tripRepository.save(trip);
        return modelMapper.map(savedTrip, TripResponseDTO.class);
    }

    public TripResponseDTO getTripById(int tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found with id: " + tripId));
        return modelMapper.map(trip, TripResponseDTO.class);
    }

    public List<TripResponseDTO> getAllTrips() {
        List<Trip> trips = tripRepository.findAll();
        return trips.stream()
                .map(trip -> modelMapper.map(trip, TripResponseDTO.class))
                .toList();
    }

    // 9. Export Trip to PDF
    public byte[] exportTripToPdf(int tripId) throws IOException {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));
        
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText("Trip Details: " + trip.getTripName());
                // Add more content (itinerary, packing list, budget)
                contentStream.endText();
            }
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            return baos.toByteArray();
        }
    }
}