package com.estudo.springboot.service;

import com.estudo.springboot.dto.UserDto;

import java.util.List;

public interface UserService
{
    UserDto createUser(UserDto user);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    List<UserDto> getAllUsersByName(String firstName);
    UserDto updateUser(UserDto userDto);
    void deleteUser(Long id);
}
