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
    EMAIL_NOT_VALID("No user found with this email", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN("Invalid or expired token", HttpStatus.FORBIDDEN),
    DOMAIN_NOT_FOUND("Domain not found", HttpStatus.NOT_FOUND),
    LOCATION_NOT_FOUND("Location not found", HttpStatus.NOT_FOUND),
    FILE_NOT_FOUND("Please select a file to upload", HttpStatus.NOT_FOUND),
    VOTE_NOT_FOUND("Vote not found", HttpStatus.NOT_FOUND),
    IMAGE_NUMBER_EXCEEDED("Maximum number of files is 5", HttpStatus.EXPECTATION_FAILED),
    FILE_IS_TOO_LARGE("File is too large, please provide up to 5 mb", HttpStatus.PAYLOAD_TOO_LARGE);

    private final String message;
    private final HttpStatus httpStatus;

    ExceptionType(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }


}
