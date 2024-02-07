package com.spotrank.core.services.impl;

import com.spotrank.core.dtos.CountryDTO;
import com.spotrank.core.mappers.CountryMapper;
import com.spotrank.core.models.Country;
import com.spotrank.core.repositories.CountryRepository;
import com.spotrank.core.services.CountryService;
import com.spotrank.core.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository repository;
    @Autowired
    UserService countryService;

    @Override
    public List<CountryDTO> findAll() {
        return CountryMapper.INSTANCE.toCountryDTOList(repository.findAll());
    }

    @Override
    public CountryDTO findById(Long id) {
        return CountryMapper.INSTANCE.toCountryDTO(repository.findById(id).orElse(null));
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
    public CountryDTO create(CountryDTO dto) {
        Country countryToSave = CountryMapper.INSTANCE.toCountryEntity(dto);
        countryToSave=repository.save(countryToSave);
        return CountryMapper.INSTANCE.toCountryDTO(countryToSave);
    }

    @Override
    public CountryDTO modify(Long id, CountryDTO dto) {
        Country old = repository.findById(id).orElse(null);
        assert old != null;
        old.setName(dto.getName());
        return CountryMapper.INSTANCE.toCountryDTO(repository.save(old));
    }
}