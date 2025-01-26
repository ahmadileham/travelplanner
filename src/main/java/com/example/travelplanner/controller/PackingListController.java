package com.example.travelplanner.controller;

import com.example.travelplanner.dto.PackingItemDTO;
import com.example.travelplanner.dto.PackingItemResponseDTO;
import com.example.travelplanner.service.PackingListService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packing-lists")
public class PackingListController {

    private final PackingListService packingListService;

    public PackingListController(PackingListService packingListService) {
        this.packingListService = packingListService;
    }

    @PostMapping("/{packingListId}/items")
    public ResponseEntity<PackingItemResponseDTO> addItem(
            @PathVariable int packingListId,
            @Valid @RequestBody PackingItemDTO itemDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(packingListService.addItem(packingListId, itemDTO));
    }

    @PutMapping("/items/{itemId}/packed")
    public ResponseEntity<Void> markItemAsPacked(@PathVariable int itemId) {
        packingListService.markItemAsPacked(itemId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/copy/{sourceTripId}/to/{targetTripId}")
    public ResponseEntity<Void> copyPackingList(
            @PathVariable int sourceTripId,
            @PathVariable int targetTripId) {
        packingListService.copyPackingList(sourceTripId, targetTripId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{packingListId}/items")
    public ResponseEntity<List<PackingItemResponseDTO>> getItems(@PathVariable int packingListId) {
        return ResponseEntity.ok(packingListService.getItemsByPackingList(packingListId));
    }
}