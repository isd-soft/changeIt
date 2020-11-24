package com.internship.changeit.dto;

import lombok.Data;

@Data
public class CommentVoteDto {
    private CommentDto comment;
    private UserDto user;
}
