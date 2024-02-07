package com.spotrank.core.services.impl;

import com.spotrank.core.dtos.SpotDTO;
import com.spotrank.core.mappers.SpotMapper;
import com.spotrank.core.models.Spot;
import com.spotrank.core.repositories.SpotRepository;
import com.spotrank.core.services.SpotService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SpotServiceImpl implements SpotService {

    @Autowired
    SpotRepository repository;

    @Override
    public List<SpotDTO> findAll() {
        return SpotMapper.INSTANCE.toSpotDTOList(repository.findAll());
    }

    @Override
    public SpotDTO findById(Long id) {
        return SpotMapper.INSTANCE.toSpotDTO(repository.findById(id).orElse(null));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        if(repository.existsById(id))
            repository.delete(Objects.requireNonNull(repository.findById(id).orElse(null)));
    }

    @Override
    public SpotDTO create(SpotDTO dto) {
        Spot SpotToSave = SpotMapper.INSTANCE.toSpotEntity(dto);
        SpotToSave=repository.save(SpotToSave);
        return SpotMapper.INSTANCE.toSpotDTO(SpotToSave);
    }

    @Override
    public SpotDTO modify(Long id, SpotDTO dto) {
        Spot old = repository.findById(id).orElse(null);
        assert old != null;
        old.setName(dto.getName());
        return SpotMapper.INSTANCE.toSpotDTO(repository.save(old));
    }
}
