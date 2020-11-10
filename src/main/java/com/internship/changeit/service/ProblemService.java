package com.internship.changeit.service;

import com.internship.changeit.model.Problem;
import com.internship.changeit.model.User;

import java.util.List;
import java.util.Optional;

public interface ProblemService {

    List<Problem> getAllProblems();

    Problem getProblemById(Long id);

    Problem addProblem(Problem p);

    Problem updateProblem(Problem p, Long id);

    void deleteProblem(Long id);
}
