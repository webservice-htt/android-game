package com.example.baobang.gameduangua.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by huuduc on 08/03/2018.
 */

public class ListLessonResponse extends BaseResponse{

    @SerializedName("results")
    private List<LessonResponse> courseInfo;

    public ListLessonResponse(int statusCode, List<LessonResponse> courseInfo) {
        super(statusCode);
        this.courseInfo = courseInfo;
    }

    public List<LessonResponse> getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(List<LessonResponse> courseInfo) {
        this.courseInfo = courseInfo;
    }
}
