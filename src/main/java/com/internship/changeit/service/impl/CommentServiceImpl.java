package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Comment;
import com.internship.changeit.repository.CommentRepository;
import com.internship.changeit.repository.UserRepository;
import com.internship.changeit.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
        List<Comment> sortedComments = this.getAllComments();
        sortedComments.sort(compareByDateDesc);
        return sortedComments
                .stream()
                .filter(comment -> comment.getProblem().getProblem_id().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public Comment saveComment(Comment comment) {
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

    public static Comparator<Comment> compareByDateDesc = (comment1, comment2) -> {

        Date created_At1 = comment1.getCreated_at();
        Date created_At2 = comment2.getCreated_at();

        return created_At2.compareTo(created_At1);
    };
}
