package com.internship.changeit.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "domain")
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long domain_id;
    private String domainName;

    @ManyToMany(mappedBy = "domains")
    private List<Problem> problems = new ArrayList<>();


    public void addProblem(Problem problem) {
        this.problems.add(problem);
        problem.getDomains().add(this);
    }

    public void removeProblem(Problem problem){
        this.problems.remove(problem);
        problem.getDomains().remove(this);
    }
}
