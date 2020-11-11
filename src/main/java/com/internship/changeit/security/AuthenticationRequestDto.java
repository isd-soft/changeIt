package com.internship.changeit.security;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
