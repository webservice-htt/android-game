package com.example.baobang.gameduangua.all_course;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.baobang.gameduangua.Constant;
import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.adapter.CourseListAdapter;
import com.example.baobang.gameduangua.all_course.detail.CourseDetailActivity;
import com.example.baobang.gameduangua.data.ApiUtils;
import com.example.baobang.gameduangua.data.SOService;
import com.example.baobang.gameduangua.listener.RecyclerItemClickListener;
import com.example.baobang.gameduangua.model.Course;
import com.example.baobang.gameduangua.model.ListCourseResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huuduc on 08/03/2018.
 */

public class ListCourseActivity extends AppCompatActivity {


    private RecyclerView rvListCourse;
    private CourseListAdapter listAdapter;
    private List<Course> listCourse;
    private SOService mService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course);

//        Toolbar toolbar = findViewById(R.id.toolBar);
//        setSupportActionBar(toolbar);

        rvListCourse = findViewById(R.id.rvListCourse);
        rvListCourse.setHasFixedSize(true);

        mService = ApiUtils.getSOService();
        listCourse = new ArrayList<>();
        listAdapter = new CourseListAdapter(this, listCourse);
        rvListCourse.setLayoutManager(new GridLayoutManager(ListCourseActivity.this, 2));
        rvListCourse.setNestedScrollingEnabled(false);
        rvListCourse.setAdapter(listAdapter);


        Log.d("CourseDescriptionFrag", "onCreate: day ne");
        mService.listAllCourse().enqueue(new Callback<ListCourseResponse>() {
            @Override
            public void onResponse(Call<ListCourseResponse> call, Response<ListCourseResponse> response) {

                if (response.isSuccessful()){
                    int statusCode = response.body().getStatuscode();
                    if (statusCode == 200){
                        listCourse.addAll(response.body().getCourseList());
                        listAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ListCourseResponse> call, Throwable t) {

            }
        });



        rvListCourse.addOnItemTouchListener(new RecyclerItemClickListener(ListCourseActivity.this, rvListCourse, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Course course = listCourse.get(position);
                Log.d("LIStView", "onItemClick: " + course.getCourseName());
                Intent detailIntent = new Intent(ListCourseActivity.this, CourseDetailActivity.class);
                detailIntent.putExtra(Constant.COURSE_ID, course.getId());
                startActivity(detailIntent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }
}