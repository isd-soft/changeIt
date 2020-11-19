package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Comment;
import com.internship.changeit.repository.CommentRepository;
import com.internship.changeit.repository.UserRepository;
import com.internship.changeit.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository){
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> getByProblem(Long id) {
        return commentRepository.findAll()
                .stream()
                .filter(comment -> comment.getProblem().getProblem_id().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public Comment saveComment(Comment comment) {
        comment.setCreated_at(new Date());
        comment.setUser(userRepository.findByEmail(comment.getUser().getEmail()).get());
        comment.setVotes(0);
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
