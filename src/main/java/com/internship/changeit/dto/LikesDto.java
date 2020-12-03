package com.internship.changeit.dto;

import lombok.Data;

@Data
public class LikesDto {
    private CommentDto comment;
    private UserDto user;
}
