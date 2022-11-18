package com.spotrank.core.mappers;

import com.spotrank.core.dtos.TeamDto;
import com.spotrank.core.models.Team;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeamMapper {
    public static TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);
    TeamDto toTeamDTO(Team team);
    Team toTeamEntity(TeamDto dto);
}
