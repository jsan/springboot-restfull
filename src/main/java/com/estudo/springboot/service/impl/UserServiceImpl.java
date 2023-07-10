package com.estudo.springboot.service.impl;

import com.estudo.springboot.dto.UserDto;
import com.estudo.springboot.entity.User;
import com.estudo.springboot.exception.EmailAlreadyExistsException;
import com.estudo.springboot.mapper.AutoUserMapper;
import com.estudo.springboot.repository.UserRepository;
import com.estudo.springboot.exception.ResourceNotFoundException;
import com.estudo.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService
{
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto)
    {
        userRepository.findByEmail(userDto.getEmailAddress())
                .ifPresent(s -> {
                    throw new EmailAlreadyExistsException("Email", userDto.getEmailAddress());
                });

        // Manually mapping: User userFromRequest = UserMapper.mapToUserJpa(userDto);
        // ModelMapper ..:User userFromRequest = modelMapper.map(userDto, User.class);
        // Used: MapStruct @Mapper on the AutoUserMapper
        User userFromRequest = AutoUserMapper.MAPPER.autoMapToUser(userDto);
        User userJpa = userRepository.save(userFromRequest);
        return AutoUserMapper.MAPPER.autoMapToUserDto(userJpa);
    }

    @Override
    public UserDto updateUser(UserDto userDto)
    {
        User userJpa = userRepository.findById(userDto.getId()).
                orElseThrow(() -> new ResourceNotFoundException("User", "ID", userDto.getId()));

        userJpa.setFirstName(userDto.getFirstName());
        userJpa.setFirstName(userDto.getFirstName());
        userJpa.setLastName(userDto.getLastName());
        userJpa.setEmail(userDto.getEmailAddress());

        return AutoUserMapper.MAPPER.autoMapToUserDto(userRepository.save(userJpa));
    }

    @Override
    public UserDto getUserById(Long userId)
    {
        User userJpa = userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
        // return UserMapper.mapToUserDto(userJpa.orElse(null));
        return AutoUserMapper.MAPPER.autoMapToUserDto(userJpa);
    }

    @Override
    public List<UserDto> getAllUsers()
    {
        List<User> users = userRepository.findAll();

        return users.stream().map(AutoUserMapper.MAPPER::autoMapToUserDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAllUsersByName(String firstName)
    {
        // List<User> usersName = userRepository.findByFirstName(firstName);
        // List<User> usersName = userRepository.findUsersByFirstname(firstName);
        List<User> usersName = userRepository.findByFirstNameAndLastName(firstName, "Mello");
        return usersName.stream().map(AutoUserMapper.MAPPER::autoMapToUserDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId)
    {
        User userJpa = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

        userRepository.deleteById(userId);
    }
}
