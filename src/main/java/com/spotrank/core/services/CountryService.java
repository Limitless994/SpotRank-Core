package com.spotrank.core.services;

import com.spotrank.core.dtos.CityInnerDTO;
import com.spotrank.core.dtos.CountryDTO;

import java.util.List;

public interface CountryService {
    List<CountryDTO> findAll();
    CountryDTO findById(Long id);
    boolean existsById(Long id);
    void delete(Long id);
    CountryDTO create(CountryDTO dto);
    CountryDTO modify(Long countryId, CountryDTO countryDTO);
    List<CityInnerDTO> getCitiesByCountry(Long countryId);
}