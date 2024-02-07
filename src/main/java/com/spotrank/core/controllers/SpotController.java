package com.spotrank.core.controllers;

import com.spotrank.core.dtos.SpotDTO;
import com.spotrank.core.services.SpotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/spot")
    @AllArgsConstructor
    public class SpotController {
        private final SpotService SpotService;

        @GetMapping
        public List<SpotDTO> getAllCountries() {
            return SpotService.findAll();
        }

        @GetMapping("/{spotId}")
        public ResponseEntity<SpotDTO> getSpotById(@PathVariable Long SpotId) {
            SpotDTO SpotDTO = SpotService.findById(SpotId);
            return ResponseEntity.ok(SpotDTO);
        }

        @PostMapping
        public ResponseEntity<SpotDTO> createSpot(@RequestBody SpotDTO SpotDTO) {
            SpotDTO createdSpot = SpotService.create(SpotDTO);
            return new ResponseEntity<>(createdSpot, HttpStatus.CREATED);
        }

        @PutMapping("/{spotId}")
        public ResponseEntity<SpotDTO> updateSpot(@PathVariable Long SpotId, @RequestBody SpotDTO SpotDTO) {
            SpotDTO updatedSpot = SpotService.modify(SpotId, SpotDTO);
            return ResponseEntity.ok(updatedSpot);
        }

        @DeleteMapping("/{spotId}")
        public ResponseEntity<Void> deleteSpot(@PathVariable Long SpotId) {
            SpotService.delete(SpotId);
            return ResponseEntity.noContent().build();
        }
    }
