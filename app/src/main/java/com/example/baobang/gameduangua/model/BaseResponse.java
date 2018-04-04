package com.example.baobang.gameduangua.model;

import com.google.gson.annotations.SerializedName;

/*
 * Created by huuduc on 09/03/2018.
 */

public class BaseResponse {
    @SerializedName("statuscode")
    private int statusCode;

    public BaseResponse(int statuscode) {
        this.statusCode = statuscode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
