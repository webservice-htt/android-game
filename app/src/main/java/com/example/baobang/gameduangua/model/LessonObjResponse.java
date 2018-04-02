package com.example.baobang.gameduangua.model;

import com.google.gson.annotations.SerializedName;

/*
 * Created by huuduc on 09/03/2018.
 */

public class LessonObjResponse extends BaseResponse {
    @SerializedName("results")
    private LessonResponse lessonResponse;

    public LessonObjResponse(int statusCode,LessonResponse lessonResponse) {
        super(statusCode);
        this.lessonResponse = lessonResponse;
    }

    public LessonResponse getLessonResponse() {
        return lessonResponse;
    }

    public void setLessonResponse(LessonResponse lessonResponse) {
        this.lessonResponse = lessonResponse;
    }
}
