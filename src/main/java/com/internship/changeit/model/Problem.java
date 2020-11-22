package com.internship.changeit.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "problem")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long problem_id;

    private String title;
    private String description;

    @Column(name = "votes_count")
    private Integer votesCount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @Type(type = "org.hibernate.type.BinaryType")
    private Byte[] image;

    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "domain_problem",
        joinColumns = @JoinColumn(name = "problem_id"),
        inverseJoinColumns = @JoinColumn(name = "domain_id"))
    private List<Domain> domains = new ArrayList<>();

    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY)
    private List<Vote> votes = new ArrayList<>();

}
