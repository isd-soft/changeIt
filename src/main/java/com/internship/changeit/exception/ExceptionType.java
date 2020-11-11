package com.internship.changeit.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionType {
    USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND),
    PROBLEM_NOT_FOUND("Problem not found", HttpStatus.NOT_FOUND),
    COMMENT_NOT_FOUND("Comment not found", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus httpStatus;

    ExceptionType(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }


}
