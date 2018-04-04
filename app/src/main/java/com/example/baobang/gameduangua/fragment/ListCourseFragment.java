package com.example.baobang.gameduangua.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.adapter.CategoryListAdapter;
import com.example.baobang.gameduangua.data.ApiUtils;
import com.example.baobang.gameduangua.data.SOService;
import com.example.baobang.gameduangua.model.Category;
import com.example.baobang.gameduangua.model.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCourseFragment extends Fragment {

    private RecyclerView mRvCategories;
    private CategoryListAdapter mListAdapter;
    private List<Category> mCategories;
    private SOService mService;

    private static ListCourseFragment listCourseFragment = new ListCourseFragment();

    public  ListCourseFragment() {
    }

    public static ListCourseFragment newInstance() {

        return listCourseFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_course, container, false);

        mRvCategories = view.findViewById(R.id.rvListCourse);
        mRvCategories.setHasFixedSize(true);

        mService = ApiUtils.getSOService();
        mCategories = new ArrayList<>();
        mListAdapter = new CategoryListAdapter(getContext(), mCategories);
        mRvCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvCategories.setNestedScrollingEnabled(false);
        mRvCategories.setAdapter(mListAdapter);
        mService.getAllCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {

                if (response.isSuccessful()){
                    int statusCode = response.body().getStatusCode();
                    if (statusCode == 200){
                        mCategories.addAll(response.body().getResults());
                        mListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });

        return view;
    }
}
