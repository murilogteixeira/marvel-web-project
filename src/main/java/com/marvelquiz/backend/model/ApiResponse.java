package com.marvelquiz.backend.model;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    private HttpStatus status;
    private String message;

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}