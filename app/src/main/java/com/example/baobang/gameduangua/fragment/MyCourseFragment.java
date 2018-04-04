package com.example.baobang.gameduangua.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baobang.gameduangua.Constant;
import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.adapter.CourseListAdapter;
import com.example.baobang.gameduangua.adapter.UserCourseListAdapter;
import com.example.baobang.gameduangua.all_course.detail.CourseDetailActivity;
import com.example.baobang.gameduangua.data.ApiUtils;
import com.example.baobang.gameduangua.data.SOService;
import com.example.baobang.gameduangua.model.Course;
import com.example.baobang.gameduangua.model.User;
import com.example.baobang.gameduangua.model.UserCourse;
import com.example.baobang.gameduangua.model.UserResponse;
import com.example.baobang.gameduangua.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MyCourseFragment extends Fragment {

    RecyclerView mRvCourse;
    UserCourseListAdapter mCourseListAdapter;
    List<UserCourse> mCourses;

    private SOService mSoService;

    public MyCourseFragment() {
        // Required empty public constructor
    }

    public static MyCourseFragment newInstance() {
        return new MyCourseFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_course, container, false);
        mRvCourse = view.findViewById(R.id.rcCourses);
        mCourses = new ArrayList<>();
        mCourseListAdapter = new UserCourseListAdapter(getContext(), mCourses);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRvCourse.setLayoutManager(layoutManager);
        mRvCourse.setAdapter(mCourseListAdapter);
        mCourseListAdapter.setOnItemClickListener(new UserCourseListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {

                UserCourse course = mCourses.get(pos);

                AppUtils.setValueToSharedPreferences(
                        getContext(),
                        Constant.KEY_PREFERENCES,
                        MODE_PRIVATE,
                        Constant.COURSE_ID,
                        course.getCourse().getId()
                );

                Intent detailIntent = new Intent(getContext(), CourseDetailActivity.class);
                detailIntent.putExtra(Constant.COURSE_ID, course.getCourse().getId());
                getContext().startActivity(detailIntent);
            }
        });

        mSoService = ApiUtils.getSOService();

        String userId = getUserId();
        mSoService.getUser(userId).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    mCourses.clear();
                    mCourses.addAll(response.body().getUser().getCourse());
                    mCourseListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });


        return view;
    }

    private String getUserId(){
        SharedPreferences preferences = getContext().getSharedPreferences(Constant.KEY_PREFERENCES, Context.MODE_PRIVATE);
        String userString = preferences.getString(Constant.USER, "");

        if(userString.isEmpty()){
            return "";
        }
        try {
            JSONObject jsonObject = new JSONObject(userString);
            return jsonObject.getString("_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}
