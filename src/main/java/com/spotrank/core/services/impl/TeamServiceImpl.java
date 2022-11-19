package com.spotrank.core.services.impl;

import com.spotrank.core.dtos.TeamDto;
import com.spotrank.core.mappers.TeamMapper;
import com.spotrank.core.models.Team;
import com.spotrank.core.models.User;
import com.spotrank.core.repositories.TeamRepository;
import com.spotrank.core.repositories.UserRepository;
import com.spotrank.core.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository repository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<TeamDto> findAll() {
        List<TeamDto> dtos = new ArrayList<>();
        for (Team t: repository.findAll()){
            dtos.add(TeamMapper.INSTANCE.toTeamDTO(t));
        }
        return dtos;
    }

    @Override
    public TeamDto findById(Long id) {
        return TeamMapper.INSTANCE.toTeamDTO(repository.findById(id).orElse(null));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
    }

    @Override
    public TeamDto create(TeamDto dto) {
        if(!repository.existsByName(dto.getName())){
            User admin = userRepository.findById(Long.parseLong(dto.getAdminId())).orElse(null);

            for (Team t: repository.findAll())
                if(t.getAdmin().getId() == admin.getId())
                    return null;

            Team teamToCreate = TeamMapper.INSTANCE.toTeamEntity(dto);
            teamToCreate.setAdmin(admin);
            teamToCreate.setCreationDate(new Date());

            Team createdTeam = repository.save(teamToCreate);
            return TeamMapper.INSTANCE.toTeamDTO(createdTeam);
        }else{
            return null;
        }
    }

    @Override
    public boolean insertMember(Long id, Long userId) {
        if(repository.existsById(id) && userRepository.existsById(userId)){
            Team team = repository.findById(id).orElse(null);
            User user = userRepository.findById(userId).orElse(null);

            if(team.getAdmin().getId() == user.getId())
                return false;

            if(user.getTeam() != null)
                return false;

            team.getMembers().add(user);
            repository.save(team);
            return true;
        }else{
            return false;
        }
    }
    @Override
    public boolean removeMember(Long id, Long userId) {
        if(repository.existsById(id) && userRepository.existsById(userId)) {
            Team team = repository.findById(id).orElse(null);
            User user = userRepository.findById(userId).orElse(null);

            if (team.getMembers().contains(user)) {
                team.getMembers().remove(user);
                repository.save(team);
                return true;
            }
        }
        return false;
    }
}