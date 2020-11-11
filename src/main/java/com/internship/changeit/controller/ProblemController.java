package com.internship.changeit.controller;

import com.internship.changeit.dto.ProblemDto;
import com.internship.changeit.mapper.ProblemMapper;
import com.internship.changeit.model.Problem;
import com.internship.changeit.service.impl.ProblemServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    private final ProblemServiceImpl problemService;

    public ProblemController(ProblemServiceImpl problemService) {
        this.problemService = problemService;
    }

    @GetMapping
    List<ProblemDto> all() {
        return problemService.getAllProblems()
                .stream()
                .map( ProblemMapper.INSTANCE::toDto )
                .collect( Collectors.toList() );
    }

    @PostMapping
    ProblemDto newProblem(@RequestBody ProblemDto newProblemDto) {
        Problem problem = ProblemMapper.INSTANCE.fromDto(newProblemDto);
        problemService.addProblem(problem);
        return newProblemDto;
    }

    @GetMapping("/{id}")
    ProblemDto one(@PathVariable Long id) {
        return ProblemMapper.INSTANCE.toDto( problemService.getProblemById(id) );
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