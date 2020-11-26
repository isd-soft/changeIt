package com.internship.changeit.dto;

import lombok.Data;

@Data
public class UserLogoDto {

    private Long id;
    private UserDto user;
    private byte[] userLogoFile;
}
