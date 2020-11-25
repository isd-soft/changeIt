package com.internship.changeit.controller;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.utils.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<Object> handleResourceNotFound(final ApplicationException ex, final WebRequest request) {
        return handleExceptionInternal(ex, apiMessage(ex), new HttpHeaders(), ex.getExceptionType().getHttpStatus(), request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(@NotNull final HttpMessageNotReadableException ex, @NotNull final HttpHeaders headers,
                                                                  @NotNull final HttpStatus status, @NotNull final WebRequest request) {
        return handleExceptionInternal(ex, apiMessage(ex), headers, BAD_REQUEST, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NotNull final MethodArgumentNotValidException ex, @NotNull final HttpHeaders headers,
                                                                  @NotNull final HttpStatus status, @NotNull final WebRequest request) {
        return handleExceptionInternal(ex, apiMessage(ex), headers, BAD_REQUEST, request);
    }


    private ApiStatus apiMessage(final ApplicationException ex) {
        final String message = ex.getExceptionType().getMessage() == null ? ex.getClass().getSimpleName() : ex.getExceptionType().getMessage();
        return new ApiStatus(ex.getExceptionType().getHttpStatus().toString(), message);
    }

    private ApiStatus apiMessage(final Exception ex) {
        final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
        return new ApiStatus(HttpStatus.BAD_REQUEST.toString(), message);
    }
}
