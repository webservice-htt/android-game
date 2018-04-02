package com.example.baobang.gameduangua.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 * Created by baobang on 4/2/18.
 */

public class CategoryRespone extends BaseResponse{
    @SerializedName("results")
    private List<Category> results = null;

    public CategoryRespone(int statusCode, List<Category> results) {
        super(statusCode);
        this.results = results;
    }

    public List<Category> getResults() {
        return results;
    }

    public void setResults(List<Category> results) {
        this.results = results;
    }
}
