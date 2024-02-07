package com.spotrank.core.controllers;

import com.spotrank.core.dtos.CityDTO;
import com.spotrank.core.services.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/city")
    @AllArgsConstructor
    public class CityController {
        private final CityService CityService;

        @GetMapping
        public List<CityDTO> getAllCountries() {
            return CityService.findAll();
        }

        @GetMapping("/{cityId}")
        public ResponseEntity<CityDTO> getCityById(@PathVariable Long CityId) {
            CityDTO CityDTO = CityService.findById(CityId);
            return ResponseEntity.ok(CityDTO);
        }

        @PostMapping
        public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO CityDTO) {
            CityDTO createdCity = CityService.create(CityDTO);
            return new ResponseEntity<>(createdCity, HttpStatus.CREATED);
        }

        @PutMapping("/{cityId}")
        public ResponseEntity<CityDTO> updateCity(@PathVariable Long CityId, @RequestBody CityDTO CityDTO) {
            CityDTO updatedCity = CityService.modify(CityId, CityDTO);
            return ResponseEntity.ok(updatedCity);
        }

        @DeleteMapping("/{cityId}")
        public ResponseEntity<Void> deleteCity(@PathVariable Long CityId) {
            CityService.delete(CityId);
            return ResponseEntity.noContent().build();
        }
    }
