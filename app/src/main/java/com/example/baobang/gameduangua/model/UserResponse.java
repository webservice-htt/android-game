package com.example.baobang.gameduangua.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huuduc on 29/01/2018.
 */

public class UserResponse {
    @SerializedName("statuscode")
    int status;

    @SerializedName("results")
    User user;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
