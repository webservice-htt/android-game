package com.example.baobang.gameduangua.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by huuduc on 09/03/2018.
 */

public class LessonResponse {
    @SerializedName("image")
    private String image;

    @SerializedName("_id")
    private String id;

    @SerializedName("tenKH")
    private String tenKH;

    @SerializedName("description")
    private String description;

    @SerializedName("lessons")
    private List<Lesson> lessons;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
