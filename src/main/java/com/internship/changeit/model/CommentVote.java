package com.internship.changeit.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "comment_votes")
public class CommentVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_vote_id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
