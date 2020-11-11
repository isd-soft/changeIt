package com.internship.changeit.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {
    USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND),
    PROBLEM_NOT_FOUND("Problem not found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXIST("User already registered with this email", HttpStatus.FORBIDDEN);

    private final String message;
    private final HttpStatus httpStatus;
}
