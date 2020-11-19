package com.internship.changeit.service;

import com.internship.changeit.model.User;
import com.internship.changeit.model.Vote;

import java.util.Arrays;
import java.util.List;

public interface VoteService {

    Vote saveVote(Vote vote);

    Long getByProblem(Long id);

    Vote getVote(Long problemId, Long userId);
}
