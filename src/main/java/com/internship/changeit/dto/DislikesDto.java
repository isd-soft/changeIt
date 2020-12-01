package com.internship.changeit.dto;

import lombok.Data;

@Data
public class DislikesDto {
    private CommentDto comment;
    private UserDto user;
}
