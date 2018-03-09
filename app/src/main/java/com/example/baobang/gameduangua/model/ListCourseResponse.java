package com.example.baobang.gameduangua.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by huuduc on 31/01/2018.
 */

public class ListCourseResponse {
    @SerializedName("statuscode")
    private int statuscode;

    @SerializedName("results")
    private List<Course> courseList;


    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
