package com.internship.changeit.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String userStatus;
    private String password;
}
