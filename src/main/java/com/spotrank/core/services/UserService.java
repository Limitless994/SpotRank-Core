package com.spotrank.core.services;

import com.spotrank.core.dtos.UserDto;
import com.spotrank.core.models.User;

import java.util.List;

public interface UserService {
List<UserDto> findAll();
UserDto findByEmail(String email);
UserDto findById(Long id);
boolean existsById(Long id);
void delete(Long id);
UserDto create(UserDto dto);
UserDto modify(Long id, UserDto dto);
}
