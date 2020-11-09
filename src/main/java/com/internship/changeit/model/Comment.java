package com.internship.changeit.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Comment extends BasicEntity {

    private Integer votes;
    private String content;
    private Timestamp created_at;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
}
