package com.vinilab.OrderManagement.exceptions.handler;

import java.time.Instant;

public class ApiError {
    private Instant timestamp;
    private int status;
    private String message;

    public ApiError(Instant timestamp, int status, String message) {
        this.timestamp = Instant.now();
        this.status = status;
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
