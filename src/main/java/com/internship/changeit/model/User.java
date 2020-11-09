package com.internship.changeit.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user")
public class User extends BasicEntity {

    private String firstName;
    private String lastName;
    private String email;
    private Boolean confirmedAccount;
    private String password;

    private Role role;

}
