package com.example.baobang.gameduangua.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by baobang on 4/4/18.
 */

public class GalleryResponse extends BaseResponse{

    @SerializedName("results")
    @Expose
    private List<Gallery> galleries = null;

    public GalleryResponse(int statuscode, List<Gallery> galleries) {
        super(statuscode);
        this.galleries = galleries;
    }

    public List<Gallery> getGalleries() {
        return galleries;
    }

    public void setGalleries(List<Gallery> galleries) {
        this.galleries = galleries;
    }
}
