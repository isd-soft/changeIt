package com.internship.changeit.dto;

import lombok.Data;

@Data
public class ResetPasswordDetailsDTO {
    private String password;
    private String token;
    private long id;
    private String passwordConfirmation;
}
