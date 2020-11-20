package com.internship.changeit.model;

import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "domain")
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long domain_id;
    private String domainName;

    @ManyToMany(mappedBy = "domains", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cache(
            usage = CacheConcurrencyStrategy.READ_WRITE)
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
