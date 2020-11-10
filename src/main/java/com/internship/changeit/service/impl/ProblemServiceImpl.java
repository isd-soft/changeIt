package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Problem;
import com.internship.changeit.model.User;
import com.internship.changeit.repository.ProblemRepository;
import com.internship.changeit.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemServiceImpl(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Override
    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    @Override
    public Problem getProblemById(Long id) {
        return problemRepository.findById(id).
                orElseThrow(() -> new ApplicationException(ExceptionType.PROBLEM_NOT_FOUND));

    }

    @Override
    public Problem addProblem(Problem p) {
        return problemRepository.save(p);
    }

    @Override
    public Problem updateProblem(Problem newProblem, Long id) {
        problemRepository.findById(id)
                .map(problem -> {
                    problem.setTitle(newProblem.getTitle());
                    problem.setDescription(newProblem.getDescription());
                    problem.setVotes(newProblem.getVotes());
                    problem.setCreated_at(newProblem.getCreated_at());
                    problem.setUpdated_at(newProblem.getUpdated_at());
                    problem.setStatus(newProblem.getStatus());
                    problem.setUser(newProblem.getUser());
                    problem.setLocation(newProblem.getLocation());
                    problem.setDistrict(newProblem.getDistrict());
                    problem.setComments(newProblem.getComments());
                    problem.setDomains(newProblem.getDomains());
                    return problemRepository.save(problem);
                })
                .orElseGet(() -> {
                    newProblem.setProblem_id(id);
                    return problemRepository.save(newProblem);
                });
        return newProblem;
    }

    @Override
    public void deleteProblem(Long id) {
        Problem problem = problemRepository.findById(id).orElseThrow(
                () -> new ApplicationException(ExceptionType.PROBLEM_NOT_FOUND));
        problemRepository.delete(problem);
    }
}
