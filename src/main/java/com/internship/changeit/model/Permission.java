package com.internship.changeit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {
    PROBLEMS_WRITE("problems:write"),
    PROBLEMS_READ("problems:read"),
    PROBLEMS_DELETE("problems:delete");


    private final String permission;
}
