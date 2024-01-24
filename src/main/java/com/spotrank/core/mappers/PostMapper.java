package com.spotrank.core.mappers;

import com.spotrank.core.dtos.PostDto;
import com.spotrank.core.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {
    public static PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    PostDto toPostDTO(Post post);
    Post toPostEntity(PostDto dto);

    List<PostDto> toPostDTOList(List<Post> all);

}
