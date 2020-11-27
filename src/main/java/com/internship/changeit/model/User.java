package com.internship.changeit.model;

import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "user_entity")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email(message = "Please provide a valid email address")
    @Pattern(regexp = ".+@.+\\..+", message = "Does not match an email pattern")
    @NotNull(message = "Email cannot be null")
    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "A password must be at least 8 characters long")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private VerificationToken verificationToken;

    @OneToMany(mappedBy = "user")
    @org.hibernate.annotations.Cache(
            usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cache(
            usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Problem> problemsAdded = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cache(
            usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Vote> votes = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CommentVote> commentVotes = new ArrayList<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserLogo userLogo;
}
