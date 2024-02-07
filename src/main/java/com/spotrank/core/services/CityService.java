package com.spotrank.core.services;

import com.spotrank.core.dtos.CityDTO;

import java.util.List;

public interface CityService {
    List<CityDTO> findAll();
    CityDTO findById(Long id);
    boolean existsById(Long id);
    void delete(Long id);
    CityDTO create(CityDTO dto);

    CityDTO modify(Long CityId, CityDTO CityDTO);
}