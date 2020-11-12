package com.internship.changeit.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiStatus {

    private final String status;
    private final String message;

    @Override
    public String toString() {
        return String.format("Api Error Status: [%s] Reason: %s", status, message);
    }
}
