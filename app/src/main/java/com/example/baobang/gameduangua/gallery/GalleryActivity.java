package com.example.baobang.gameduangua.gallery;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.baobang.gameduangua.R;
import com.example.baobang.gameduangua.adapter.GalleryAdapter;
import com.example.baobang.gameduangua.data.ApiUtils;
import com.example.baobang.gameduangua.data.SOService;
import com.example.baobang.gameduangua.model.Gallery;
import com.example.baobang.gameduangua.model.GalleryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryActivity extends AppCompatActivity {

    private RecyclerView mRvGallery;
    private GalleryAdapter mGalleryAdapter;
    private List<Gallery> mGalleries;
    private SOService mSoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        mSoService = ApiUtils.getSOService();
        addControls();
    }

    private void addControls() {
        mRvGallery = findViewById(R.id.rvGallery);
        mGalleries = new ArrayList<>();
        mGalleryAdapter = new GalleryAdapter(this, mGalleries);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRvGallery.setLayoutManager(gridLayoutManager);
        mRvGallery.setAdapter(mGalleryAdapter);

        mSoService.getAllGallery().enqueue(new Callback<GalleryResponse>() {
            @Override
            public void onResponse(@NonNull Call<GalleryResponse> call, @NonNull Response<GalleryResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(GalleryActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(response.body().getStatusCode() == 200){
                    mGalleries.addAll(response.body().getGalleries());
                    mGalleryAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(GalleryActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GalleryResponse> call, @NonNull Throwable t) {

            }
        });

    }
}
