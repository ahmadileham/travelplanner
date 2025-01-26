package com.example.travelplanner.service;

import org.springframework.stereotype.Service;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.example.travelplanner.dto.ActivityDTO;
import com.example.travelplanner.dto.ActivityResponseDTO;
import com.example.travelplanner.exception.ResourceNotFoundException;
import com.example.travelplanner.models.Activity;
import com.example.travelplanner.models.Itinerary;
import com.example.travelplanner.repository.ActivityRepository;
import com.example.travelplanner.repository.ItineraryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItineraryService {
    private ItineraryRepository itineraryRepository;
    private ActivityRepository activityRepository;
    private ModelMapper modelMapper;

    public ItineraryService(ItineraryRepository itineraryRepository,
                           ActivityRepository activityRepository,
                           ModelMapper modelMapper) {
        this.itineraryRepository = itineraryRepository;
        this.activityRepository = activityRepository;
        this.modelMapper = modelMapper;
    }

    // 2. Add itinerary (handled automatically with trip creation)
    // Add activity to itinerary
    public ActivityResponseDTO addActivity(int itineraryId, ActivityDTO activityDTO) {
        Itinerary itinerary = itineraryRepository.findById(itineraryId)
                .orElseThrow(() -> new ResourceNotFoundException("Itinerary not found"));
        
        Activity activity = modelMapper.map(activityDTO, Activity.class);
        activity.setItinerary(itinerary);
        Activity savedActivity = activityRepository.save(activity);
        
        return modelMapper.map(savedActivity, ActivityResponseDTO.class);
    }

    public List<ActivityResponseDTO> getActivitiesByItinerary(int itineraryId) {
        // Verify itinerary exists
        itineraryRepository.findById(itineraryId)
                .orElseThrow(() -> new ResourceNotFoundException("Itinerary not found with id: " + itineraryId));

        List<Activity> activities = activityRepository.findByItineraryId(itineraryId);
        return activities.stream()
                .map(activity -> modelMapper.map(activity, ActivityResponseDTO.class))
                .toList();
    }
}
