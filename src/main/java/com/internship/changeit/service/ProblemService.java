package com.internship.changeit.service;

import com.internship.changeit.model.Problem;
import com.internship.changeit.model.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;

import java.util.List;


public interface ProblemService {

    List<Problem> getAllProblems(Pageable pageable);

    List<Problem> sortProblemsByDateAsc();

    List<Problem> sortProblemsByDateDesc();

    List<Problem> sortProblemsByVoteAsc();

    List<Problem> sortProblemsByVoteDesc();

    Problem getProblemById(Long id);

    Problem addProblem(Problem problem);

    Problem updateProblem(Problem p, Long id);

    void deleteProblem(Long id);

    Problem updateProblemStatus(Long id, Status status);
}