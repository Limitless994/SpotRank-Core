package com.spotrank.core.controllers;

import com.spotrank.core.dtos.CityDTO;
import com.spotrank.core.dtos.CityInnerDTO;
import com.spotrank.core.dtos.CountryDTO;
import com.spotrank.core.services.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/country")
    @AllArgsConstructor
    public class CountryController {
        private final CountryService countryService;

        @GetMapping
        public List<CountryDTO> getAllCountries() {
            return countryService.findAll();
        }

        @GetMapping("/{countryId}")
        public ResponseEntity<CountryDTO> getCountryById(@PathVariable Long countryId) {
            CountryDTO countryDTO = countryService.findById(countryId);
            return ResponseEntity.ok(countryDTO);
        }

        @PostMapping
        public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) {
            CountryDTO createdCountry = countryService.create(countryDTO);
            return new ResponseEntity<>(createdCountry, HttpStatus.CREATED);
        }

        @PutMapping("/{countryId}")
        public ResponseEntity<CountryDTO> updateCountry(@PathVariable Long countryId, @RequestBody CountryDTO countryDTO) {
            CountryDTO updatedCountry = countryService.modify(countryId, countryDTO);
            return ResponseEntity.ok(updatedCountry);
        }

        @DeleteMapping("/{countryId}")
        public ResponseEntity<Void> deleteCountry(@PathVariable Long countryId) {
            countryService.delete(countryId);
            return ResponseEntity.noContent().build();
        }

    @GetMapping("/{countryId}/cities")
    public ResponseEntity<List<CityInnerDTO>> getCitiesByCountry(@PathVariable Long countryId) {
        List<CityInnerDTO> cities = countryService.getCitiesByCountry(countryId);
        return ResponseEntity.ok(cities);
    }
    }
