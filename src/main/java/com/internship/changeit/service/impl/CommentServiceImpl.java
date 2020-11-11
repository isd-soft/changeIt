package com.internship.changeit.service.impl;

import com.internship.changeit.model.Comment;
import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.service.CommentService;
import com.internship.changeit.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment saveComment(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.findById(id).
                orElseThrow(() -> new ApplicationException(ExceptionType.COMMENT_NOT_FOUND));
        commentRepository.deleteById(id);
    }
}
