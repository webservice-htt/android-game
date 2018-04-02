package com.example.baobang.gameduangua.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/*
 * Created by baobang on 4/2/18.
 */

public class Category {
    @SerializedName("_id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("course")
    private ArrayList<Course> courses;

    public Category(String id, String name, ArrayList<Course> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
