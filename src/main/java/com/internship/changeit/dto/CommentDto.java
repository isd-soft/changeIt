package com.internship.changeit.dto;


import com.internship.changeit.model.Problem;
import com.internship.changeit.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {

    private Long comment_id;
    private Integer votes;
    private String content;
    private Date created_at;
    private User user;
    private Problem problem;
}
