package com.internship.changeit.controller;

import com.internship.changeit.dto.CommentDto;
import com.internship.changeit.dto.PaginationDetailsDto;
import com.internship.changeit.dto.ProblemDto;
import com.internship.changeit.dto.UserDto;
import com.internship.changeit.mapper.CommentMapper;
import com.internship.changeit.mapper.ProblemMapper;
import com.internship.changeit.mapper.UserMapper;
import com.internship.changeit.model.Problem;
import com.internship.changeit.service.impl.CommentServiceImpl;
import com.internship.changeit.service.impl.ProblemServiceImpl;
import com.internship.changeit.service.impl.VoteServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @PostMapping
    public ResponseEntity<?> all(@RequestBody final PaginationDetailsDto paginationDetails) {
        final Map<Object, Object> response = new HashMap<>();
        final Page<Problem> problemPageResponse = problemService.getAllProblems(paginationDetails.getPage(), paginationDetails.getSize(), paginationDetails.getSortDir(), paginationDetails.getSort());
        List<ProblemDto> problems =  problemPageResponse.stream()
                                                        .map(ProblemMapper.INSTANCE::toDto )
                                                        .collect(Collectors.toList());
        response.put("Problems" , problems);
        response.put("totalPages", problemPageResponse.getTotalPages());
        response.put("hasNext", problemPageResponse.hasNext());
        response.put("hasPrevious", problemPageResponse.hasPrevious());
        response.put("totalElements", problemPageResponse.getTotalElements());

        return ResponseEntity.ok(response);
    }



    @GetMapping("/{id}/comments")
    public List<CommentDto> getCommentsByProblem(@PathVariable Long id) {
        return commentService.getByProblem(id)
                .stream()
                .map(CommentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/user")
    public UserDto getUserCreator(@PathVariable Long id) {
        return UserMapper.INSTANCE.toDto(problemService.getProblemById(id).getUser());
    }

    @GetMapping("/{id}/votes")
    public Long getVotesByProblem(@PathVariable Long id) {
        return voteService.getByProblem(id);
    }

    @PostMapping("/new")
    public ProblemDto newProblem(@RequestBody ProblemDto newProblemDto) {
        Problem problem = ProblemMapper.INSTANCE.fromDto(newProblemDto);

        problemService.addProblem(problem);
        newProblemDto.setId(problem.getId());
        newProblemDto.setCreatedAt(problem.getCreatedAt());
        newProblemDto.setUpdatedAt(problem.getUpdatedAt());
        return newProblemDto;
    }

    @GetMapping("/{id}")
    public ProblemDto one(@PathVariable Long id) {
        return ProblemMapper.INSTANCE.toDto(problemService.getProblemById(id));
    }

    @PutMapping("/{id}")
    public ProblemDto replaceProblem(@RequestBody ProblemDto newProblemDto, @PathVariable Long id) {
        Problem newProblem = ProblemMapper.INSTANCE.fromDto(newProblemDto);
        problemService.updateProblem(newProblem, id);
        return newProblemDto;
    }

    @DeleteMapping("/{id}")
    void deleteProblem(@PathVariable Long id) {
        problemService.deleteProblem(id);
    }

}