package com.internship.changeit.controller;


import com.internship.changeit.dto.ProblemDto;
import com.internship.changeit.dto.VoteDto;
import com.internship.changeit.mapper.ProblemMapper;
import com.internship.changeit.mapper.VoteMapper;
import com.internship.changeit.model.Problem;
import com.internship.changeit.model.Vote;
import com.internship.changeit.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/vote")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @GetMapping("{problemId}/{userId}")
    VoteDto getVote(@PathVariable Long problemId, @PathVariable Long userId) {

        return VoteMapper.INSTANCE.toDto(voteService.getVote(problemId, userId));
    }

    @PostMapping
    VoteDto vote(@RequestBody VoteDto voteDto) {
        Vote vote = VoteMapper.INSTANCE.fromDto(voteDto);
        return VoteMapper.INSTANCE.toDto(voteService.saveVote(vote));
    }

}
