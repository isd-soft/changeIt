package com.internship.changeit.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {

    private Long comment_id;
    private Integer votes;
    private String content;
    private Date created_at;
}
