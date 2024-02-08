package com.spotrank.core.mappers;

import com.spotrank.core.dtos.PostDto;
import com.spotrank.core.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "spotId", source = "id")
    PostDto toPostDTO(Post post);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "id", source = "spotId")
    Post toPostEntity(PostDto dto);

    List<PostDto> toPostDTOList(List<Post> all);
}
