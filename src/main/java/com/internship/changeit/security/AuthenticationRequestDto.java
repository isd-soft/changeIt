package com.internship.changeit.security;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AuthenticationRequestDto {

    @Email(message = "Please provide a valid email address")
    @Pattern(regexp = ".+@.+\\..+", message = "Does not match an email pattern")
    @NotNull(message = "Username can not be empty")
    private String email;
    private String password;
}
