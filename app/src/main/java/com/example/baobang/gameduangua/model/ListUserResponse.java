package com.example.baobang.gameduangua.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by huuduc on 29/01/2018.
 */

public class ListUserResponse {
    @SerializedName("statuscode")
    int status;

    @SerializedName("results")
    ArrayList<User> user;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<User> getUser() {
        return user;
    }

    public void setUser(ArrayList<User> user) {
        this.user = user;
    }
}
