package com.gmail.chernii.oleksii.dto;

import com.gmail.chernii.oleksii.dto.annotations.PasswordMatches;
import com.gmail.chernii.oleksii.dto.annotations.ValidEmail;
import com.gmail.chernii.oleksii.enities.Role;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@PasswordMatches
@Accessors(chain = true)
public class UserDto{
    private Long id;

    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;
    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;
    private Set<Role> roles;
}
