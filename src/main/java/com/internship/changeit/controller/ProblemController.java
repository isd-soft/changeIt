package com.internship.changeit.controller;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Problem;
import com.internship.changeit.repository.ProblemRepository;
import com.internship.changeit.service.impl.ProblemServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    private final ProblemServiceImpl problemService;

    public ProblemController(ProblemServiceImpl problemService) {
        this.problemService = problemService;
    }

    @GetMapping
    List<Problem> all() {
        return problemService.getAllProblems();
    }

    @PostMapping
    void newProblem(@RequestBody Problem newProblem) {
        problemService.addProblem(newProblem);
    }

    // Single item

    @GetMapping("/{id}")
    Problem one(@PathVariable Long id) {

        return problemService.getProblemById(id);
    }

    @PutMapping("/{id}")
    void replaceProblem(@RequestBody Problem newProblem, @PathVariable Long id) {
        problemService.updateProblem(newProblem, id);
    }

    @DeleteMapping("/{id}")
    void deleteProblem(@PathVariable Long id) {
        problemService.deleteProblem(id);
    }

}
