package com.estudo.springboot.mapper;

import com.estudo.springboot.dto.UserDto;
import com.estudo.springboot.entity.User;

// This class was manually created and is not being used
// Mapstruct is being used instead
public abstract class UserMapper
{
    // UserJpa -> UserDto
    public static UserDto mapToUserDto(User userJpa)
    {
        UserDto userDto = new UserDto(
                userJpa.getId(),
                userJpa.getFirstName(),
                userJpa.getLastName(),
                userJpa.getEmail());
        return userDto;
    }

    // UserDto -> UserJpa
    public static User mapToUserJpa(UserDto userDto)
    {
        User userJpa = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmailAddress());
        return userJpa;
    }
}
