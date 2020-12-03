package com.internship.changeit.dto;

import lombok.Data;

@Data
public class ResetPasswordDetailsDTO {
    private long id;
    private String password;
    private String token;
    private String passwordConfirmation;
}
