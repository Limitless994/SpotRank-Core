package com.spotrank.core.services.impl;

import com.spotrank.core.dtos.UserDto;
import com.spotrank.core.mappers.UserMapper;
import com.spotrank.core.models.User;
import com.spotrank.core.repositories.UserRepository;
import com.spotrank.core.services.UserService;
import com.spotrank.core.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    WalletService walletService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<UserDto> findAll() {
        List<User> list = repository.findAll();
        List<UserDto> out = new ArrayList<>();
        for (User user: list
             ) {
            out.add(UserMapper.INSTANCE.toUserDTO(user));

        }
        return out;
    }

    @Override
    public UserDto findByEmail(String email) {
        return UserMapper.INSTANCE.toUserDTO(repository.findByEmail(email).orElse(null));
    }

    @Override
    public UserDto findById(Long id) {
        return UserMapper.INSTANCE.toUserDTO(repository.findById(id).orElse(null));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        User userToDelete = repository.findById(id).orElse(null);
        if(userToDelete != null)
            repository.delete(userToDelete);
    }

    @Override
    public UserDto create(UserDto dto) {

        User userToCreate = UserMapper.INSTANCE.toUserEntity(dto);
        userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));
        userToCreate=repository.save(userToCreate);

        walletService.create(userToCreate.getId(), userToCreate);

        return UserMapper.INSTANCE.toUserDTO(userToCreate);

    }

    @Override
    public UserDto modify(Long id, UserDto dto) {
            User oldUser = repository.findById(id).orElse(null);
                oldUser.setEmail(dto.getEmail());
                oldUser.setAge(dto.getAge());
                oldUser.setSurname(dto.getSurname());
                oldUser.setName(dto.getName());
                return UserMapper.INSTANCE.toUserDTO(repository.save(oldUser));
    }
}
