package com.internship.changeit.service;

import com.internship.changeit.model.CommentVote;

public interface CommentVoteService {

    CommentVote saveVote(CommentVote commentVote);

    Long getByComment(Long id);

    CommentVote getVote(Long commentId, Long userId);

    void deleteCommentVote(Long id);
}
