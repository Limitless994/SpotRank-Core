package com.spotrank.core.mappers;

import com.spotrank.core.dtos.UserDto;
import com.spotrank.core.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    public static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toUserDTO(User user);

    List<UserDto> toUserDTOList(List<User> list);

    User toUserEntity(UserDto dto);

}
