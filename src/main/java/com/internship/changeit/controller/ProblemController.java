package com.internship.changeit.controller;

import com.internship.changeit.dto.CommentDto;
import com.internship.changeit.dto.ProblemDto;
import com.internship.changeit.dto.UserDto;
import com.internship.changeit.dto.VoteDto;
import com.internship.changeit.mapper.CommentMapper;
import com.internship.changeit.mapper.ProblemMapper;
import com.internship.changeit.mapper.UserMapper;
import com.internship.changeit.mapper.VoteMapper;
import com.internship.changeit.model.Problem;
import com.internship.changeit.model.User;
import com.internship.changeit.model.Vote;
import com.internship.changeit.service.impl.CommentServiceImpl;
import com.internship.changeit.service.impl.ProblemServiceImpl;
import com.internship.changeit.service.impl.VoteServiceImpl;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/problem")
public class ProblemController {

    private final ProblemServiceImpl problemService;
    private final CommentServiceImpl commentService;
    private final VoteServiceImpl voteService;

    public ProblemController(ProblemServiceImpl problemService, CommentServiceImpl commentService, VoteServiceImpl voteService) {
        this.problemService = problemService;
        this.commentService = commentService;
        this.voteService = voteService;
    }

    @GetMapping
    List<ProblemDto> all() {
        return problemService.getAllProblems()
                .stream()
                .map(ProblemMapper.INSTANCE::toDto )
                .collect( Collectors.toList() );
    }

    @GetMapping("/sortedByDateAsc")
    List<ProblemDto> allSortedByDateAsc() {
        return problemService.sortProblemsByDateAsc()
                .stream()
                .map(ProblemMapper.INSTANCE::toDto )
                .collect( Collectors.toList() );
    }

    @GetMapping("/sortedByDateDesc")
    List<ProblemDto> allSortedByDateDesc() {
        return problemService.sortProblemsByDateDesc()
                .stream()
                .map(ProblemMapper.INSTANCE::toDto )
                .collect( Collectors.toList() );
    }

    @GetMapping("/sortedByVoteAsc")
    List<ProblemDto> allSortedByVoteAsc() {
        return problemService.sortProblemsByVoteAsc()
                .stream()
                .map(ProblemMapper.INSTANCE::toDto )
                .collect( Collectors.toList() );
    }

    @GetMapping("/sortedByVoteDesc")
    List<ProblemDto> allSortedByVoteDesc() {
        return problemService.sortProblemsByVoteDesc()
                .stream()
                .map(ProblemMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/comments")
    List<CommentDto> getCommentsByProblem(@PathVariable Long id) {
        return commentService.getByProblem(id)
                .stream()
                .map(CommentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/votes")
    Long getVotesByProblem(@PathVariable Long id) {
        return voteService.getByProblem(id);
    }

    @PostMapping
    ProblemDto newProblem(@RequestBody ProblemDto newProblemDto) {
        Problem problem = ProblemMapper.INSTANCE.fromDto(newProblemDto);
        problemService.addProblem(problem);
        return newProblemDto;
    }

    @GetMapping("/{id}")
    ProblemDto one(@PathVariable Long id) {
        return ProblemMapper.INSTANCE.toDto(problemService.getProblemById(id));
    }

    @PutMapping("/{id}")
    ProblemDto replaceProblem(@RequestBody ProblemDto newProblemDto, @PathVariable Long id) {
        Problem newProblem = ProblemMapper.INSTANCE.fromDto(newProblemDto);
        problemService.updateProblem(newProblem, id);
        return newProblemDto;
    }

    @DeleteMapping("/{id}")
    void deleteProblem(@PathVariable Long id) {
        problemService.deleteProblem(id);
    }

}