package com.spotrank.core.services;

import com.spotrank.core.dtos.TeamDto;
import com.spotrank.core.dtos.UserDto;

import java.util.List;

public interface TeamService {
    List<TeamDto> findAll();
    TeamDto findById(Long id);
    boolean existsById(Long id);
    void delete(Long id);
    TeamDto create(TeamDto dto);
    boolean insertMember(Long id, Long userId);
    boolean removeMember(Long id, Long userId);
}
