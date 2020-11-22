package com.internship.changeit.service.impl;

import com.internship.changeit.model.Vote;
import com.internship.changeit.repository.ProblemRepository;
import com.internship.changeit.repository.VoteRepository;
import com.internship.changeit.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final ProblemRepository problemRepository;

    @Override
    public Vote saveVote(Vote vote) {
        if (
                voteRepository.findAll()
                .stream()
                .anyMatch(vot -> vot.getProblem().equals(vote.getProblem())
                        && vot.getUser().equals(vote.getUser()))
        ) {
            return new Vote();
        } else {
            return voteRepository.save(vote);
        }
    }

    @Override
    public Long getByProblem(Long id) {
        return voteRepository.findAll()
                .stream()
                .filter(vote -> vote.getProblem().getProblem_id().equals(id))
                .count();
    }

    @Override
    public Vote getVote(Long problemId, Long userId) {
        return voteRepository.findAll()
                .stream()
                .filter(vot -> vot.getProblem().getProblem_id().equals(problemId) && vot.getUser().getUser_id().equals(userId) )
                .findAny().orElse(null);
    }

}
