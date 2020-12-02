package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Comment;
import com.internship.changeit.model.User;
import com.internship.changeit.repository.CommentRepository;
import com.internship.changeit.repository.UserRepository;
import com.internship.changeit.service.CommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    @Override
    public List<Comment> getByProblem(Long id) {
        List<Comment> sortedComments = this.commentRepository.findAll();
        sortedComments.sort(compareByDateDesc);
        return sortedComments
                .stream()
                .filter(comment -> comment.getProblem().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> getByUser(Long id) {
        List<Comment> sortedComments = this.commentRepository.findAll();
        sortedComments.sort(compareByDateDesc);
        return sortedComments
                .stream()
                .filter(comment -> comment.getUser().getUser_id().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).
                orElseThrow(() -> new ApplicationException(ExceptionType.COMMENT_NOT_FOUND));
    }

    @Override
    public Comment saveComment(Comment comment) {
        final Optional<User> user = userRepository.findByEmail(comment.getUser().getEmail());
        user.ifPresent(comment::setUser);
        comment.setVotes(0);
        comment.setCreated_at(new Date());
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public Comment updateComment(Comment newComment, Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if(optionalComment.isPresent()){
            Comment updatable = optionalComment.get();
            updatable.setVotes(newComment.getVotes());
            updatable.setContent(newComment.getContent());
            commentRepository.save(updatable);
            return updatable;
        } else throw new ApplicationException(ExceptionType.COMMENT_NOT_FOUND);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('comments:delete')")
    public void deleteComment(long id) {
        final Comment comment = commentRepository.findById(id).
                orElseThrow(() -> new ApplicationException(ExceptionType.COMMENT_NOT_FOUND));
        commentRepository.delete(comment);
    }

    public static Comparator<Comment> compareByDateDesc = (comment1, comment2) -> {

        Date created_At1 = comment1.getCreated_at();
        Date created_At2 = comment2.getCreated_at();

        return created_At2.compareTo(created_At1);
    };
}
