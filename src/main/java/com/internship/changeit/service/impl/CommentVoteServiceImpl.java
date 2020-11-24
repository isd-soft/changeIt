package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.CommentVote;
import com.internship.changeit.repository.CommentVoteRepository;
import com.internship.changeit.service.CommentVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentVoteServiceImpl implements CommentVoteService {

    private final CommentVoteRepository commentVoteRepository;

    @Override
    public CommentVote saveVote(CommentVote commentVote) {
        if (
                commentVoteRepository.findAll()
                        .stream()
                        .anyMatch(vote -> vote.getComment().equals(commentVote.getComment())
                                && vote.getUser().equals(commentVote.getUser()))
        ) {
            return new CommentVote();
        } else {
            return commentVoteRepository.save(commentVote);
        }
    }

    @Override
    public Long getByComment(Long id) {
        return commentVoteRepository.findAll()
                .stream()
                .filter(vote -> vote.getComment().getComment_id().equals(id))
                .count();
    }

    @Override
    public CommentVote getVote(Long commentId, Long userId) {
        return commentVoteRepository.findAll()
                .stream()
                .filter(vote -> vote.getComment().getComment_id().equals(commentId) && vote.getUser().getUser_id().equals(userId) )
                .findAny().orElse(null);
    }

    @Override
    public void deleteCommentVote(Long id) {
        commentVoteRepository.findById(id).
                orElseThrow(() -> new ApplicationException(ExceptionType.VOTE_NOT_FOUND));
        commentVoteRepository.deleteById(id);
    }
}
