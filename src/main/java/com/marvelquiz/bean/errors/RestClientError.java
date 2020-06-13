package com.marvelquiz.bean.errors;

import java.util.ArrayList;

public class RestClientError {
    private String status;
    private ArrayList<RestClientErrorDescription> description;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<RestClientErrorDescription> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<RestClientErrorDescription> description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Status: " + status + ", Description: " + description;
    }
}