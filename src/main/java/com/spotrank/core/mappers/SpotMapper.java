package com.spotrank.core.mappers;

import com.spotrank.core.dtos.SpotDTO;
import com.spotrank.core.models.Spot;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface SpotMapper {
    public static SpotMapper INSTANCE = Mappers.getMapper(SpotMapper.class);
    SpotDTO toSpotDTO(Spot spot);

    List<SpotDTO> toSpotDTOList(List<Spot> spots);

    Spot toSpotEntity(SpotDTO spotDTO);
}
