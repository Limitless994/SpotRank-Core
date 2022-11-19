package com.spotrank.core.controllers;

import com.spotrank.core.dtos.UserDto;
import com.spotrank.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {
        if(userService.existsById(id))
            return new ResponseEntity<UserDto>(userService.findById(id), HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @GetMapping("all")
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<List<UserDto>>(userService.findAll(), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<UserDto> getByEmail(@RequestParam(value = "email") String email) {
        if(userService.findByEmail(email) != null) {
            return new ResponseEntity<UserDto>(userService.findByEmail(email), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        if(userService.findByEmail(userDto.getEmail()) == null) {
            return new ResponseEntity<UserDto>(userService.create(userDto), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> modify(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        if(userService.findById(id) != null) {
            return new ResponseEntity<UserDto>(userService.modify(id, userDto), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        if(userService.findById(id) != null){
            userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
