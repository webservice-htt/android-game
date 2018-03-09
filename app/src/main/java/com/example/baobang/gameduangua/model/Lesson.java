package com.example.baobang.gameduangua.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huuduc on 08/03/2018.
 */

public class Lesson  {
    @SerializedName("_id")
    private String lessonId;

    @SerializedName("tenBH")
    private String tenBH;

    @SerializedName("url")
    private String lessonUrl;

    public Lesson() {
    }

    public Lesson(String lessonId, String tenBH, String lessonUrl) {
        this.lessonId = lessonId;
        this.tenBH = tenBH;
        this.lessonUrl = lessonUrl;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getTenBH() {
        return tenBH;
    }

    public void setTenBH(String tenBH) {
        this.tenBH = tenBH;
    }

    public String getLessonUrl() {
        return lessonUrl;
    }

    public void setLessonUrl(String lessonUrl) {
        this.lessonUrl = lessonUrl;
    }
}
