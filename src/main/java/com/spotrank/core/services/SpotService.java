package com.spotrank.core.services;

import com.spotrank.core.dtos.SpotDTO;

import java.util.List;

public interface SpotService {
    List<SpotDTO> findAll();
    SpotDTO findById(Long spotId);
    SpotDTO create(SpotDTO spotDTO);
    SpotDTO modify(Long spotId, SpotDTO spotDTO);

    boolean existsById(Long id);

    void delete(Long spotId);
}