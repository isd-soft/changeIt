package com.internship.changeit.service;

import com.internship.changeit.model.Vote;

public interface VoteService {

    Vote saveVote(Vote vote);

    Long getByProblem(Long id);

    Vote getVote(Long problemId, Long userId);
}
