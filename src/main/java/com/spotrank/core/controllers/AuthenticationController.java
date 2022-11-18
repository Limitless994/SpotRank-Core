package com.spotrank.core.controllers;

import com.spotrank.core.dtos.AuthenticationDto;
import com.spotrank.core.services.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @PostMapping
    public String login(@RequestBody AuthenticationDto authDto) {
        return this.authenticationService.login(authDto.getEmail(), authDto.getPassword());
    }
}
