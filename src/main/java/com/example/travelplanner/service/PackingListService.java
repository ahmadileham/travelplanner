package com.example.travelplanner.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.travelplanner.models.PackingList;
import com.example.travelplanner.dto.PackingItemDTO;
import com.example.travelplanner.dto.PackingItemResponseDTO;
import com.example.travelplanner.exception.ResourceNotFoundException;
import com.example.travelplanner.models.PackingItem;
import com.example.travelplanner.repository.PackingItemRepository;
import com.example.travelplanner.repository.PackingListRepository;
import com.example.travelplanner.repository.TripRepository;
import org.modelmapper.ModelMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PackingListService {
    private PackingListRepository packingListRepository;
    private PackingItemRepository packingItemRepository;
    private TripRepository tripRepository;
    private ModelMapper modelMapper;

    // Constructor injection
    public PackingListService(PackingListRepository packingListRepository,
                             PackingItemRepository packingItemRepository,
                             ModelMapper modelMapper) {
        this.packingListRepository = packingListRepository;
        this.packingItemRepository = packingItemRepository;
        this.modelMapper = modelMapper;
    }

    // Add Item
    public PackingItemResponseDTO addItem(int packingListId, PackingItemDTO itemDTO) {
        PackingList packingList = packingListRepository.findById(packingListId)
                .orElseThrow(() -> new ResourceNotFoundException("Packing list not found"));
        
        PackingItem newItem = modelMapper.map(itemDTO, PackingItem.class);
        newItem.setPackingList(packingList);
        PackingItem savedItem = packingItemRepository.save(newItem);
        return modelMapper.map(savedItem, PackingItemResponseDTO.class);
    }

    // Get Items
    public List<PackingItemResponseDTO> getItemsByPackingList(int packingListId) {
        List<PackingItem> items = packingItemRepository.findByPackingListId(packingListId);
        return items.stream()
                .map(item -> modelMapper.map(item, PackingItemResponseDTO.class))
                .toList();
    }

    // 3. Add PackingList (called automatically when creating trip)
    // 4. Load from existing trips
    public void copyPackingList(int sourceTripId, int targetTripId) {
        PackingList source = tripRepository.findById(sourceTripId)
                .orElseThrow(() -> new ResourceNotFoundException("Source trip not found"))
                .getPackingList();
        
        PackingList target = tripRepository.findById(targetTripId)
                .orElseThrow(() -> new ResourceNotFoundException("Target trip not found"))
                .getPackingList();

        List<PackingItem> copiedItems = source.getPackingItems().stream()
                .map(item -> {
                    PackingItem newItem = new PackingItem();
                    newItem.setItemName(item.getItemName());
                    newItem.setQuantity(item.getQuantity());
                    newItem.setPackingList(target);
                    return newItem;
                }).toList();

        packingItemRepository.saveAll(copiedItems);
    }

    // 5. Mark item as packed
    public void markItemAsPacked(int itemId) {
        PackingItem item = packingItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Packing item not found"));
        item.setPacked(true);
        packingItemRepository.save(item);
    }
}
