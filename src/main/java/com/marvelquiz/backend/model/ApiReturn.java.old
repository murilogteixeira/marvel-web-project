package com.marvelquiz.backend.model;

import com.marvelquiz.bean.errors.RestClientError;

import org.springframework.http.HttpStatus;

public class ApiReturn<T> {
    private HttpStatus status;
    private String message;
    private RestClientError error;
    private DataReturn<T> data;

    public HttpStatus getStatus() {
        return status;
    }

    public DataReturn<T> getData() {
        return data;
    }

    public void setData(DataReturn<T> data) {
        this.data = data;
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

    public RestClientError getError() {
        return error;
    }

    public void setError(RestClientError error) {
        this.error = error;
    }

}