package com.internship.changeit.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionType {
    USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND),
    PROBLEM_NOT_FOUND("Problem not found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXIST("User already registered with this email", HttpStatus.FORBIDDEN),
    COMMENT_NOT_FOUND("Comment not found", HttpStatus.NOT_FOUND),
    INVALID_ARGUMENTS("Provided inputs are not valid", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_USER("Authentication is required to access the resource", HttpStatus.UNAUTHORIZED),
    DISTRICT_NOT_FOUND("District not found", HttpStatus.NOT_FOUND),
    LOCATION_NOT_FOUND("Location not found", HttpStatus.NOT_FOUND),
    EMAIL_NOT_VALID("No user founder with this email", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN("Invalid or expired token", HttpStatus.FORBIDDEN);

    private final String message;
    private final HttpStatus httpStatus;

    ExceptionType(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }


}
