package com.marvelquiz.backend.model;

public class MarvelReturn<T> {
    private String status;
    private String copyright;
    private DataReturn<T> data;
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public DataReturn<T> getData() {
        return data;
    }

    public void setData(DataReturn<T> data) {
        this.data = data;
    }
}