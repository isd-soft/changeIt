package com.internship.changeit.service;

import com.internship.changeit.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComments();

    Comment saveComment(Comment comment);

}
