package com.spotrank.core.services.impl;

import com.spotrank.core.dtos.PostDto;
import com.spotrank.core.dtos.PostDto;
import com.spotrank.core.mappers.PostMapper;
import com.spotrank.core.mappers.TeamMapper;
import com.spotrank.core.mappers.UserMapper;
import com.spotrank.core.models.Post;
import com.spotrank.core.repositories.PostRepository;
import com.spotrank.core.services.PostService;
import com.spotrank.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository repository;
    @Autowired
    UserService userService;

    @Override
    public List<PostDto> findAll() {
        return PostMapper.INSTANCE.toPostDTOList(repository.findAll());
    }

    @Override
    public PostDto findById(Long id) {
        return PostMapper.INSTANCE.toPostDto(repository.findById(id).orElse(null));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        if(repository.existsById(id))
            repository.delete(repository.findById(id).orElse(null));
    }

    @Override
    public PostDto create(PostDto dto) {
        Post postToSave = PostMapper.INSTANCE.toPostEntity(dto);
        postToSave.setUser(UserMapper.INSTANCE.toUserEntity(userService.findById(dto.getUserId())));
        postToSave.setCreationDate(new Date());
        postToSave = repository.save(postToSave);
        return PostMapper.INSTANCE.toPostDto(postToSave);
    }
}