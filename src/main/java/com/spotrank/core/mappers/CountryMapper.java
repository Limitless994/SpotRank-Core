package com.spotrank.core.mappers;

import com.spotrank.core.dtos.CountryDTO;
import com.spotrank.core.models.Country;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CountryMapper {
    public static CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);
    CountryDTO toCountryDTO(Country Country);
    Country toCountryEntity(CountryDTO dto);

    List<CountryDTO> toCountryDTOList(List<Country> all);
}