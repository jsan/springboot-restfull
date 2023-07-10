package com.estudo.springboot.mapper;

import com.estudo.springboot.dto.UserDto;
import com.estudo.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper
{
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    @Mapping(target = "emailAddress", source = "email")
    UserDto autoMapToUserDto(User user);

    @Mapping(target = "email", source = "emailAddress")
    User autoMapToUser(UserDto userDto);
}
