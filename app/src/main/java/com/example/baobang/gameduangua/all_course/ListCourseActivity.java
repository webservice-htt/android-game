package com.example.baobang.gameduangua.all_course;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.baobang.gameduangua.Constant;
import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.adapter.CategoryListAdapter;
import com.example.baobang.gameduangua.adapter.CourseListAdapter;
import com.example.baobang.gameduangua.all_course.detail.CourseDetailActivity;
import com.example.baobang.gameduangua.data.ApiUtils;
import com.example.baobang.gameduangua.data.SOService;
import com.example.baobang.gameduangua.listener.RecyclerItemClickListener;
import com.example.baobang.gameduangua.login.view.LoginActivity;
import com.example.baobang.gameduangua.model.Category;
import com.example.baobang.gameduangua.model.CategoryRespone;
import com.example.baobang.gameduangua.model.Course;
import com.example.baobang.gameduangua.model.ListCourseResponse;
import com.example.baobang.gameduangua.utils.AppUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Created by huuduc on 08/03/2018.
 */

public class ListCourseActivity extends AppCompatActivity {


    private RecyclerView mRvCategories;
    private CategoryListAdapter mListAdapter;
    private List<Category> mCategories;
    private SOService mService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course);

//        Toolbar toolbar = findViewById(R.id.toolBar);
//        setSupportActionBar(toolbar);

        mRvCategories = findViewById(R.id.rvListCourse);
        mRvCategories.setHasFixedSize(true);

        mService = ApiUtils.getSOService();
        mCategories = new ArrayList<>();
        mListAdapter = new CategoryListAdapter(this, mCategories);
        mRvCategories.setLayoutManager(new LinearLayoutManager(this));
        mRvCategories.setNestedScrollingEnabled(false);
        mRvCategories.setAdapter(mListAdapter);


        Log.d("CourseDescriptionFrag", "onCreate: day ne");
        mService.getAllCategories().enqueue(new Callback<CategoryRespone>() {
            @Override
            public void onResponse(Call<CategoryRespone> call, Response<CategoryRespone> response) {

                if (response.isSuccessful()){
                    int statusCode = response.body().getStatusCode();
                    if (statusCode == 200){
                        mCategories.addAll(response.body().getResults());
                        mListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryRespone> call, Throwable t) {

            }
        });



//        rvListCourse.addOnItemTouchListener(new RecyclerItemClickListener(ListCourseActivity.this, rvListCourse, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Course course = listCourse.get(position);
//                Log.d("LIStView", "onItemClick: " + course.getCourseName());
//
//                AppUtils.setValueToSharedPreferences(
//                        getApplicationContext(),
//                        Constant.KEY_PREFERENCES,
//                        MODE_PRIVATE,
//                        Constant.COURSE_ID,
//                        course.getId()
//                );
//
//                Intent detailIntent = new Intent(ListCourseActivity.this, CourseDetailActivity.class);
//                detailIntent.putExtra(Constant.COURSE_ID, course.getId());
//                startActivity(detailIntent);
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//
//            }
//        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.navigation_home:
                goToHomeActivity();
                break;
            case R.id.logout:
                logOut();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToHomeActivity() {
        Intent  intent = new Intent(this, ListCourseActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void logOut() {
        SharedPreferences preferences = getSharedPreferences(
                Constant.KEY_PREFERENCES,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.USER, "");
        editor.apply();
        Intent  intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
