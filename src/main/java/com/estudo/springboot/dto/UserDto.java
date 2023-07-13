package com.estudo.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "UserDTO model information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    @Schema(description = "ID auto increment")
    private Long id;

    @Schema(description = "First Name information")
    @NotEmpty (message = "First name cannot be null or empty")
    private String firstName;

    @Schema(description = "Last Name information")
    @NotEmpty (message = "Last name cannot be null or empty")
    private String lastName;

    @Schema(description = "Email information")
    @Email
    @NotEmpty (message = "Email cannot be invalid :)")
    private String emailAddress;
}
