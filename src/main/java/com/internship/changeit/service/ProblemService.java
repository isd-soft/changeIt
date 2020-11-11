package com.internship.changeit.service;

import com.internship.changeit.model.Problem;

import java.util.List;

public interface ProblemService {

    List<Problem> getAllProblems();

    Problem getProblemById(Long id);

    void saveOrCreate(Problem p);

    void deleteProblem(Long id);
}
