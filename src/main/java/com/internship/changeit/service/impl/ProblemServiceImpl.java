package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Domain;
import com.internship.changeit.model.Problem;
import com.internship.changeit.model.Status;
import com.internship.changeit.repository.DomainRepository;
import com.internship.changeit.repository.ProblemRepository;
import com.internship.changeit.service.ProblemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;
    private final DomainRepository domainRepository;

    public ProblemServiceImpl(ProblemRepository problemRepository, DomainRepository domainRepository) {
        this.problemRepository = problemRepository;
        this.domainRepository = domainRepository;
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
    public Problem addProblem(Problem problem) {
        List<Domain> domains = new ArrayList<>();
        problem.getDomains().forEach(x -> {
            Domain domain = domainRepository.getOne(x.getDomain_id());
            if (domain != null){
                domains.add(domain);
            }
        });
        problem.setDistrict(problem.getLocation().getDistrict());
        problem.setDomains(domains);
        problemRepository.save(problem);
        return problem;
    }

    @Override
    public Problem updateProblem(Problem newProblem, Long id) {

        Optional<Problem> optionalProblem = problemRepository.findById(id);

        if(optionalProblem.isPresent()){
            Problem updatable = optionalProblem.get();
            updatable.setTitle(newProblem.getTitle());
            updatable.setDescription(newProblem.getDescription());
            updatable.setVotesCount(newProblem.getVotesCount());
            updatable.setCreated_at(newProblem.getCreated_at());
            updatable.setUpdated_at(newProblem.getUpdated_at());
            updatable.setStatus((newProblem.getStatus()));
            problemRepository.save(updatable);
            return updatable;
        } else throw new ApplicationException(ExceptionType.PROBLEM_NOT_FOUND);
    }

    @Override
    public void deleteProblem(Long id) {
        Optional<Problem> problem = problemRepository.findById(id);

        if(problem.isPresent()){
            problemRepository.deleteById(id);
        } else throw new ApplicationException(ExceptionType.PROBLEM_NOT_FOUND);
    }

    @Override
    public Problem updateProblemStatus(Long id, Status status) {
        Problem problem = problemRepository.findById(id).orElseThrow(
                () -> new ApplicationException(ExceptionType.PROBLEM_NOT_FOUND));
        problem.setStatus(status);
        this.problemRepository.save(problem);
        return problem;
    }

}