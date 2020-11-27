package com.internship.changeit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {
    COMMENTS_DELETE("comments:delete"),
    VOTES_DELETE("delete:vote"),
    PROBLEM_PROPERTIES_READ("problem_properties:read"),
    PROBLEM_PROPERTIES_CRUD("problem_properties:CRUD"),
    USER_CRUD("user:crud");

    private final String permission;
}
