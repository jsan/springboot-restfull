package com.estudo.springboot.service;

import com.estudo.springboot.dto.UserDto;
import com.estudo.springboot.entity.User;

import java.util.List;

public interface UserService
{
    User createUser(User user);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    List<UserDto> getAllUsersByName(String firstName);
    UserDto updateUser(UserDto userDto);
    void deleteUser(Long id);
}
