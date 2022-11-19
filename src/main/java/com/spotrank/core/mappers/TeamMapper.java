package com.spotrank.core.mappers;

import com.spotrank.core.dtos.TeamDto;
import com.spotrank.core.models.Team;
import com.spotrank.core.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;

@Mapper
public interface TeamMapper {
    public static TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);
    @Mapping(target = "adminId", source = "admin.id")
    @Mapping(target = "membersId", source = "members", qualifiedByName = "mapMembers" )
    TeamDto toTeamDTO(Team team);
    Team toTeamEntity(TeamDto dto);

    @Named("mapMembers")
    default Set<Long> mapProducts(Set<User> members){
        Set<Long> membersIdList = new HashSet<>();
        for (User u: members)
            membersIdList.add(u.getId());

        return membersIdList;
    }
}
