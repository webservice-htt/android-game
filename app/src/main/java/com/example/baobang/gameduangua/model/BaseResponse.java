package com.example.baobang.gameduangua.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huuduc on 09/03/2018.
 */

public class BaseResponse {
    @SerializedName("statuscode")
    private int statuscode;

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }
}
