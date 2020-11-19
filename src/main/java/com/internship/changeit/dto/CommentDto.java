package com.internship.changeit.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {

    private Long comment_id;
    private ProblemDto problem;
    private UserDto user;
    private Integer votes;
    private String content;
    private Date created_at;
}
