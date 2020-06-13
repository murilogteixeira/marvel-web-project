package com.marvelquiz.bean.comics2;

import java.util.ArrayList;

public class ComicData {
    private int total;
    private ArrayList<Comic> results;

    public ArrayList<Comic> getResults() {
        return results;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setResults(ArrayList<Comic> results) {
        this.results = results;
    }
}