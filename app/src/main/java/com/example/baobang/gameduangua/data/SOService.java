package com.example.baobang.gameduangua.data;

import com.example.baobang.gameduangua.model.CategoryRespone;
import com.example.baobang.gameduangua.model.LessonObjResponse;
import com.example.baobang.gameduangua.model.ListCourseResponse;
import com.example.baobang.gameduangua.model.ListUserResponse;
import com.example.baobang.gameduangua.model.UserRequest;
import com.example.baobang.gameduangua.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SOService {
    @POST("/user/login")
    Call<UserResponse> login(@Body UserRequest body);

    @POST("/user/register")
    Call<UserResponse> register(@Body UserRequest body);

    @GET("/user")
    Call<ListUserResponse> listAllUser();

    @GET("/user/{userID}")
    Call<UserResponse> getUser(@Path("userID") String userID);

    @GET("/course")
    Call<ListCourseResponse> listAllCourse();

    @GET("/course/{courseID}")
    Call<LessonObjResponse> getLessonById(@Path("courseID") String courseID);

    @GET("/category")
    Call<CategoryRespone> getAllCategories();
}
