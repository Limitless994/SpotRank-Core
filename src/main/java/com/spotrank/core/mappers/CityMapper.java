package com.spotrank.core.mappers;

import com.spotrank.core.dtos.CityDTO;
import com.spotrank.core.dtos.CityInnerDTO;
import com.spotrank.core.models.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CityMapper {
    public static CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
    CityDTO toCityDTO(City City);
    City toCityEntity(CityDTO dto);
    List<CityDTO> toCityDTOList(List<City> all);
    List<CityInnerDTO> toCityInnerDTOList(List<City> all);

}