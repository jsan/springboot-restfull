package com.estudo.springboot.controller;

import com.estudo.springboot.dto.UserDto;
import com.estudo.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs to User resource",
        description = "CRUD, Create user, Read user, Update user and Delete user"
)
@RestController
// @ControllerAdvice // Document it how it works
// @AllArgsConstructor
@RequestMapping("users")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @Operation(
            summary = "Create User API",
            description = "To save a new user on the User table"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status User created"
    )
    @PostMapping("create")
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserDto userDto)
    {
        UserDto serviceUser = userService.createUser(userDto);
        return new ResponseEntity<>(serviceUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update User API",
            description = "To update a new user on the User table"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDto userDto)
    {
        userDto.setId(userId);
        UserDto updatedUser = userService.updateUser(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(
            summary = "Get User API",
            description = "To get a single user on the User table"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 Sucess"
    )
    @GetMapping({"{id}"})
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id)
    {
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "Get All Users API",
            description = "To get all users on the User table"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 SUCCESS"
    )
    @GetMapping("all")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> listUsers = userService.getAllUsers();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @Operation(
            summary = "Get user by name API",
            description = "To get user on the User table"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 SUCCESS"
    )
    @GetMapping("allNames/{firstName}")
    public ResponseEntity<List<UserDto>> getAllUsersByName(@PathVariable String firstName)
    {
        List<UserDto> listUsers = userService.getAllUsersByName(firstName);
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User API",
            description = "To delete a user on the User table"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 SUCCESS"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return new ResponseEntity<>("Deleted ok", HttpStatus.OK);
    }

    // @ExceptionHandler(ResourceNotFoundException.class)
    // public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest)
    // {
    //     ErrorDetails errorDetails = new ErrorDetails(
    //             LocalDateTime.now(),
    //             exception.getMessage(),
    //             webRequest.getDescription(false),
    //             "ERROU!"
    //     );
    //     return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    // }
}
