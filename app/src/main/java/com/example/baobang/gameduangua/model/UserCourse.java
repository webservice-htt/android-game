package com.example.baobang.gameduangua.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/*
 * Created by baobang on 4/4/18.
 */

public class UserCourse implements Serializable{
    @SerializedName("status")
    private Integer status;
    @SerializedName("_id")
    private String _id;
    @SerializedName("courseId")
    private Course course;

    public UserCourse() {
    }

    public UserCourse(Integer status, String _id, Course course) {
        this.status = status;
        this._id = _id;
        this.course = course;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
