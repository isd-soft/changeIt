package com.internship.changeit.controller;

import com.internship.changeit.dto.VoteDto;
import com.internship.changeit.mapper.VoteMapper;
import com.internship.changeit.model.Vote;
import com.internship.changeit.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/vote")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @GetMapping("{problemId}/{userId}")
    public VoteDto getVote(@PathVariable Long problemId, @PathVariable Long userId) {
        return VoteMapper.INSTANCE.toDto(voteService.getVote(problemId, userId));
    }

    @PostMapping
    public VoteDto vote(@RequestBody VoteDto voteDto) {
        final Vote vote = VoteMapper.INSTANCE.fromDto(voteDto);
        return VoteMapper.INSTANCE.toDto(voteService.saveVote(vote));
    }

}
