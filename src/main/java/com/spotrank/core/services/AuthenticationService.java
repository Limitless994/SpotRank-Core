package com.spotrank.core.services;

import com.spotrank.core.dtos.UserAuthenticationResponseDto;

public interface AuthenticationService {
    public UserAuthenticationResponseDto login(String email, String password);
}
