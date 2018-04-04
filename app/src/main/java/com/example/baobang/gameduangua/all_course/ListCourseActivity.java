package com.example.baobang.gameduangua.all_course;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.baobang.gameduangua.Constant;
import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.adapter.CategoryListAdapter;
import com.example.baobang.gameduangua.data.ApiUtils;
import com.example.baobang.gameduangua.data.SOService;
import com.example.baobang.gameduangua.fragment.ListCourseFragment;
import com.example.baobang.gameduangua.fragment.MyCourseFragment;
import com.example.baobang.gameduangua.gallery.GalleryActivity;
import com.example.baobang.gameduangua.login.view.LoginActivity;
import com.example.baobang.gameduangua.model.Category;
import com.example.baobang.gameduangua.model.CategoryResponse;
import com.example.baobang.gameduangua.profile.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Created by huuduc on 08/03/2018.
 */

public class ListCourseActivity extends AppCompatActivity {
    private static final String SELECTED_ITEM = "arg_selected_item";
    private BottomNavigationView navigation;
    private int mSelectedItem;
//    private RecyclerView mRvCategories;
//    private CategoryListAdapter mListAdapter;
//    private List<Category> mCategories;
//    private SOService mService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course);

//        Toolbar toolbar = findViewById(R.id.toolBar);
//        setSupportActionBar(toolbar);

//        mRvCategories = findViewById(R.id.rvListCourse);
//        mRvCategories.setHasFixedSize(true);
//
//        mService = ApiUtils.getSOService();
//        mCategories = new ArrayList<>();
//        mListAdapter = new CategoryListAdapter(this, mCategories);
//        mRvCategories.setLayoutManager(new LinearLayoutManager(this));
//        mRvCategories.setNestedScrollingEnabled(false);
//        mRvCategories.setAdapter(mListAdapter);
//
//
//        Log.d("CourseDescriptionFrag", "onCreate: day ne");
//        mService.getAllCategories().enqueue(new Callback<CategoryResponse>() {
//            @Override
//            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
//
//                if (response.isSuccessful()){
//                    int statusCode = response.body().getStatusCode();
//                    if (statusCode == 200){
//                        mCategories.addAll(response.body().getResults());
//                        mListAdapter.notifyDataSetChanged();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CategoryResponse> call, Throwable t) {
//
//            }
//        });

        addControlls(savedInstanceState);
        addEvents();
    }
    private void addEvents() {
        navigation.setOnNavigationItemSelectedListener(item -> {
            selectedFragment(item);
            return true;
        });
    }
    private void addControlls(Bundle savedInstanceState) {
        navigation = findViewById(R.id.navigation);
        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = navigation.getMenu().findItem(mSelectedItem);
        } else {
            selectedItem = navigation.getMenu().getItem(0);
        }
        selectedFragment(selectedItem);
    }

    private void selectedFragment(MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.navigation_home:
                fragment =  ListCourseFragment.newInstance();
                break;
            case R.id.my_course:
                fragment = MyCourseFragment.newInstance();
                break;
        }
        // update selected item
        mSelectedItem = item.getItemId();
        if(fragment != null){
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment).commit();
        }
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
            case R.id.profile:
                goToProfileActivity();
                break;
            case R.id.gallery:
                goToGalleryActivity();
                break;
            case R.id.logout:
                logOut();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToGalleryActivity() {
        Intent  intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }

    private void goToProfileActivity() {
        Intent  intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
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
