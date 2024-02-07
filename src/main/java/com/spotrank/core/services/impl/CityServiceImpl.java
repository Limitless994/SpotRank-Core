package com.spotrank.core.services.impl;

import com.spotrank.core.dtos.CityDTO;
import com.spotrank.core.mappers.CityMapper;
import com.spotrank.core.models.City;
import com.spotrank.core.repositories.CityRepository;
import com.spotrank.core.services.CityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository repository;

    @Override
    public List<CityDTO> findAll() {
        return CityMapper.INSTANCE.toCityDTOList(repository.findAll());
    }

    @Override
    public CityDTO findById(Long id) {
        return CityMapper.INSTANCE.toCityDTO(repository.findById(id).orElse(null));
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
    public CityDTO create(CityDTO dto) {
        City CityToSave = CityMapper.INSTANCE.toCityEntity(dto);
        CityToSave=repository.save(CityToSave);
        return CityMapper.INSTANCE.toCityDTO(CityToSave);
    }

    @Override
    public CityDTO modify(Long id, CityDTO dto) {
        City old = repository.findById(id).orElse(null);
        assert old != null;
        old.setName(dto.getName());
        return CityMapper.INSTANCE.toCityDTO(repository.save(old));
    }
}