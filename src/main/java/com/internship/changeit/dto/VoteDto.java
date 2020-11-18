package com.internship.changeit.dto;

import lombok.Data;

@Data
public class VoteDto {
    private ProblemDto problem;
    private UserDto user;
}
