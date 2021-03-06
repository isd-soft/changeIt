package com.internship.changeit.model;

import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "problem")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Column(name = "votes_count")
    private Integer votesCount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images =new ArrayList<>();

    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cache(
            usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "domain_problem",
        joinColumns = @JoinColumn(name = "problem_id"),
        inverseJoinColumns = @JoinColumn(name = "domain_id"))
    @org.hibernate.annotations.Cache(
            usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Domain> domains = new ArrayList<>();

    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cache(
            usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Vote> votes = new ArrayList<>();


    @PrePersist
    public void prePersist(){
        this.createdAt = Date.from(Instant.now());
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = Date.from(Instant.now());
    }
}
