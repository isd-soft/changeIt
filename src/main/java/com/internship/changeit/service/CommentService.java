package com.internship.changeit.service;

import com.internship.changeit.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComments();

    List<Comment> getByProblem(Long id);

    Comment saveComment(Comment comment);

    void deleteComment(long id);
}
