package com.internship.changeit.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "dislikes")
public class Dislikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dislike_id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
