package com.internship.changeit.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "problem")
public class Problem extends BasicEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "votes")
    private Integer votes;

    @Column(name = "created_at")
    private Timestamp created_at;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    @Column(name = "status_id")
    private Integer status_id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "location_id")
    @OneToOne
    private Location location;

    @JoinColumn(name = "location_id")
    @OneToOne
    private District district;

}
