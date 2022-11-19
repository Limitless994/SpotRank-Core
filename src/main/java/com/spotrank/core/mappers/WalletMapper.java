package com.spotrank.core.mappers;

import com.spotrank.core.dtos.UserDto;
import com.spotrank.core.dtos.WalletDto;
import com.spotrank.core.models.User;
import com.spotrank.core.models.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WalletMapper {
    public static WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

    @Mapping(target = "userId", source = "owner.id")
    WalletDto toWalletDTO(Wallet wallet);

    List<WalletDto> toWalletDtoList(List<User> list);

    Wallet toWalletEntity(WalletDto dto);

}
