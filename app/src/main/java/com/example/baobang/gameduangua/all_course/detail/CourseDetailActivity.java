package com.example.baobang.gameduangua.all_course.detail;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.baobang.gameduangua.Constant;
import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.adapter.MyPagerAdapter;
import com.example.baobang.gameduangua.all_course.ListCourseActivity;
import com.example.baobang.gameduangua.base.BaseFragment;
import com.example.baobang.gameduangua.data.ApiUtils;
import com.example.baobang.gameduangua.data.SOService;
import com.example.baobang.gameduangua.fragment.CourseDescriptionFragment;
import com.example.baobang.gameduangua.fragment.LessonFragment;
import com.example.baobang.gameduangua.model.LessonObjResponse;
import com.example.baobang.gameduangua.model.LessonResponse;
import com.example.baobang.gameduangua.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huuduc on 08/03/2018.
 */

    public class CourseDetailActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SOService mService;
    private String courseID;
    private ImageView imvCourse;

    private List<BaseFragment> listFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);
        listFragments = new ArrayList<>();
        mService = ApiUtils.getSOService();

            Constant constant = new Constant();
            String courseId = constant.getCourseSelectedId();
            if(!courseId.equals("")){
                mService.getLessonById(courseId).enqueue(new Callback<LessonObjResponse>() {
                    @Override
                    public void onResponse(Call<LessonObjResponse> call, Response<LessonObjResponse> response) {
                        if (response.isSuccessful()){

                            onGetLessonSuccess(response.body().getLessonResponse());
                            String url = response.body().getLessonResponse().getImage();
                            Glide.with(CourseDetailActivity.this).load(url).into(imvCourse);

                        }
                    }

                    @Override
                    public void onFailure(Call<LessonObjResponse> call, Throwable t) {

                    }
                });
            }

//        Intent intent = getIntent();
//        if (intent.hasExtra(Constant.COURSE_ID)){
//            courseID = intent.getStringExtra(Constant.COURSE_ID);
//            Log.d("CourseID", "onCreate: " + courseID);
//
//            mService.getLessonById(courseID).enqueue(new Callback<LessonObjResponse>() {
//                @Override
//                public void onResponse(Call<LessonObjResponse> call, Response<LessonObjResponse> response) {
//                    if (response.isSuccessful()){
//
//                        onGetLessonSuccess(response.body().getLessonResponse());
//                        String url = response.body().getLessonResponse().getImage();
//                        Glide.with(CourseDetailActivity.this).load(url).into(imvCourse);
//
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<LessonObjResponse> call, Throwable t) {
//
//                }
//            });
//        }
    }

    private void onGetLessonSuccess(LessonResponse lessonResponse) {
//        for(int i = 0 ; i < listFragments.size(); i++){
//            listFragments.get(i).onUpdateData(lessonResponse);
//        }
        addControls(lessonResponse);
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls(LessonResponse lessonResponse) {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        imvCourse = findViewById(R.id.imvCourse);

        CourseDescriptionFragment courseDescriptionFragment = CourseDescriptionFragment.newInstance(lessonResponse.getDescription());
        LessonFragment lecturesFragment = LessonFragment.newInstance(lessonResponse.getLessons());
        listFragments.add(courseDescriptionFragment);
        listFragments.add(lecturesFragment);

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), listFragments));
        tabLayout.setupWithViewPager(viewPager);
    }
}
