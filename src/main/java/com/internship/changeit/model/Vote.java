package com.internship.changeit.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vote_id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
