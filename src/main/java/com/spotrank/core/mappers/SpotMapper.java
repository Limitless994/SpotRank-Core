package com.spotrank.core.mappers;

import com.spotrank.core.dtos.SpotDTO;
import com.spotrank.core.models.Spot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SpotMapper {
    SpotMapper INSTANCE = Mappers.getMapper(SpotMapper.class);

    @Mapping(source = "city.id", target = "cityId")
    SpotDTO toSpotDTO(Spot spot);

    List<SpotDTO> toSpotDTOList(List<Spot> spots);

    @Mapping(target = "city.id", source = "cityId")
    Spot toSpotEntity(SpotDTO spotDTO);
}