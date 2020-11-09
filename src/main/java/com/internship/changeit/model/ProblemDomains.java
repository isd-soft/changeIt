package com.internship.changeit.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProblemDomains extends BasicEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "domain_id")
    private Domain domain;
}
