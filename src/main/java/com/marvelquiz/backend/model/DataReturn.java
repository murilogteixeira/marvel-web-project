package com.marvelquiz.backend.model;

import java.util.ArrayList;

public class DataReturn<T> {
    private int offset;
    private int limit;
    private int total;
    private int count;
    private ArrayList<T> results;

    public int getCount() {
        return count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<T> getResults() {
        return results;
    }

    public void setResults(ArrayList<T> results) {
        this.results = results;
    }

    public void setCount(int count) {
        this.count = count;
    }
}