package com.example.baobang.gameduangua.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("statuscode")
    @Expose
    private int statusCode;
    @SerializedName("results")
    @Expose
    private String results;

    public LoginResponse(int statusCode, String results) {
        this.statusCode = statusCode;
        this.results = results;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
