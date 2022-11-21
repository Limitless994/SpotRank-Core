package com.spotrank.core.services;

import com.spotrank.core.dtos.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();
    PostDto findById(Long id);
    boolean existsById(Long id);
    void delete(Long id);
    PostDto create(PostDto dto);
}
