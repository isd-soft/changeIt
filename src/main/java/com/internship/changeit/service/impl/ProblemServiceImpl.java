package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Domain;
import com.internship.changeit.model.Problem;
import com.internship.changeit.model.Status;
import com.internship.changeit.repository.DomainRepository;
import com.internship.changeit.repository.ProblemRepository;
import com.internship.changeit.service.ProblemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;
    private final DomainRepository domainRepository;

    @Override
    public Page<Problem> getAllProblems(final int page, final int size, final String sortDir, final String sort) {
        final PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        return problemRepository.findAll(pageRequest);

    }

    @Override
    public List<Problem> getAll(){
        return problemRepository.findAll();
    }

    @Override
    public List<Problem> sortProblemsByDateAsc(){
        List<Problem> sortedProblems = problemRepository.findAll();
        sortedProblems.sort(compareByDateAsc);
        return sortedProblems;
    }

    @Override
    public List<Problem> sortProblemsByDateDesc(){
        List<Problem> sortedProblems = this.problemRepository.findAll();
        sortedProblems.sort(compareByDateDesc);
        return sortedProblems;
    }

    @Override
    public List<Problem> sortProblemsByVoteAsc(){
        List<Problem> sortedProblems = this.problemRepository.findAll();
        sortedProblems.sort(compareByVotesAsc);
        return sortedProblems;
    }

    @Override
    public List<Problem> sortProblemsByVoteDesc(){
        List<Problem> sortedProblems = this.problemRepository.findAll();
        sortedProblems.sort(compareByVotesDesc);
        return sortedProblems;
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
            domains.add(domain);
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
            final Problem updatable = optionalProblem.get();
            updatable.setTitle(newProblem.getTitle());
            updatable.setDescription(newProblem.getDescription());
            updatable.setVotesCount(newProblem.getVotesCount());
            updatable.setStatus((newProblem.getStatus()));
            problemRepository.save(updatable);
            return updatable;
        } else throw new ApplicationException(ExceptionType.PROBLEM_NOT_FOUND);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('problem_properties:CRUD')")
    public void deleteProblem(Long id) {
        Optional<Problem> problem = problemRepository.findById(id);

        if(problem.isPresent()){
            problemRepository.deleteById(id);
        } else throw new ApplicationException(ExceptionType.PROBLEM_NOT_FOUND);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('problem_properties:CRUD')")
    public Problem updateProblemStatus(Long id, Status status) {
        final Problem problem = problemRepository.findById(id).orElseThrow(
                () -> new ApplicationException(ExceptionType.PROBLEM_NOT_FOUND));
        problem.setStatus(status);
        this.problemRepository.save(problem);
        return problem;
    }

    public static Comparator<Problem> compareByVotesAsc = (problem1, problem2) -> {
        Integer votesCount1 = problem1.getVotesCount();
        Integer votesCount2 = problem2.getVotesCount();
        return votesCount1.compareTo(votesCount2);
    };

    public static Comparator<Problem> compareByVotesDesc = (problem1, problem2) -> {
        Integer votesCount1 = problem1.getVotesCount();
        Integer votesCount2 = problem2.getVotesCount();
        return votesCount2.compareTo(votesCount1);
    };

    public static Comparator<Problem> compareByDateAsc = (problem1, problem2) -> {
        Date created_At1 = problem1.getCreatedAt();
        Date created_At2 = problem2.getCreatedAt();
        return created_At1.compareTo(created_At2);
    };

    public static Comparator<Problem> compareByDateDesc = (problem1, problem2) -> {
        Date created_At1 = problem1.getCreatedAt();
        Date created_At2 = problem2.getCreatedAt();
        return created_At2.compareTo(created_At1);
    };

    public List<Problem> getByUser(Long id) {
        List<Problem> problems = this.problemRepository.findAll();
        return problems
                .stream()
                .filter(problem -> problem.getUser().getUser_id().equals(id))
                .collect(Collectors.toList());
    }
}