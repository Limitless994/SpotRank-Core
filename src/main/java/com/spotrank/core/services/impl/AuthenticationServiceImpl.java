package com.spotrank.core.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.spotrank.core.dtos.UserAuthenticationResponseDto;
import com.spotrank.core.models.User;
import com.spotrank.core.repositories.UserRepository;
import com.spotrank.core.security.JwtProvider;
import com.spotrank.core.services.AuthenticationService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Value("${security.token-expiration}")
    private Integer tokenExpiration;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserAuthenticationResponseDto login(String email, String password) {
        User user = this.userRepository.findByEmail(email).orElse(null);
        if (!this.passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
        }
        ObjectNode userNode = new ObjectMapper().convertValue(user, ObjectNode.class);
        userNode.remove("password");
        Map<String, Object> claimMap = new HashMap<>(0);
        claimMap.put("user", userNode);
        String token = JwtProvider.createJwt(email, claimMap, tokenExpiration);
        String tokenExpirationDate = DateTime.now().plusDays(tokenExpiration).toString();
        return new UserAuthenticationResponseDto(token, tokenExpirationDate, user.getId(), user.getName(), user.getEmail());
    }
}
