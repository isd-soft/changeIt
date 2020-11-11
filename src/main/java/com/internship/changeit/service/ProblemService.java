package com.internship.changeit.service;

import com.internship.changeit.model.Problem;

import java.util.List;


public interface ProblemService {

    List<Problem> getAllProblems();

    Problem getProblemById(Long id);

    Problem addProblem(Problem problem);

    Problem updateProblem(Problem p, Long id);

    void deleteProblem(Long id);
}