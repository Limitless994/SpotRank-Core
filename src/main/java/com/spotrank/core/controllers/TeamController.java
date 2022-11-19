package com.spotrank.core.controllers;

import com.spotrank.core.dtos.TeamDto;
import com.spotrank.core.services.TeamService;
import com.spotrank.core.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("team")
public class TeamController {

    private static final Logger log = LogManager.getLogger(TeamController.class);

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<TeamDto> getById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(this.teamService.findById(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<TeamDto>> getAll() {
        return new ResponseEntity<>(this.teamService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeamDto> create(@RequestBody TeamDto dto) {
        return new ResponseEntity<>(this.teamService.create(dto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if(teamService.existsById(id)){
            this.teamService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("add")
    public ResponseEntity<TeamDto> addMember(@RequestParam("id") Long id,@RequestParam("userId") Long userId) {
        if(teamService.insertMember(id, userId))
            return new ResponseEntity<>(teamService.findById(id), HttpStatus.OK);
        else
            return new ResponseEntity<>(teamService.findById(id), HttpStatus.BAD_REQUEST);
    }
    @PutMapping("remove")
    public ResponseEntity<TeamDto> removeMember(@RequestParam("id") Long id,@RequestParam("userId") Long userId) {
        if(teamService.removeMember(id, userId))
            return new ResponseEntity<>(teamService.findById(id), HttpStatus.OK);
        else
            return new ResponseEntity<>(teamService.findById(id), HttpStatus.BAD_REQUEST);
    }
}
