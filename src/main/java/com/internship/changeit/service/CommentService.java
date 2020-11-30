package com.internship.changeit.service;

import com.internship.changeit.model.Comment;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComments(final Pageable pageable);

    List<Comment> getByProblem(Long id);

    Comment getCommentById(Long id);

    Comment saveComment(Comment comment);

    Comment updateComment(Comment comment, Long id);

    void deleteComment(long id);

    List<Comment> getByUser(Long id);
}
