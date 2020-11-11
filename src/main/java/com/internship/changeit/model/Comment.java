package com.internship.changeit.model;

import lombok.Data;
import org.mapstruct.Builder;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long comment_id;

    private Integer votes;
    private String content;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

}
