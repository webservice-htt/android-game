package com.example.baobang.gameduangua.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by huuduc on 31/01/2018.
 */

public class Course {

    @SerializedName("_id")
    private String id;

    @SerializedName("tenKH")
    private String courseName;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String imgUrl;

    @SerializedName("lessons")
    private ArrayList<Lesson> listLesson;

    public Course() {
    }

    public Course(String id, String courseName, String description, String imgUrl, ArrayList<Lesson> listLesson) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.imgUrl = imgUrl;
        this.listLesson = listLesson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ArrayList<Lesson> getListLesson() {
        return listLesson;
    }

    public void setListLesson(ArrayList<Lesson> listLesson) {
        this.listLesson = listLesson;
    }
}
