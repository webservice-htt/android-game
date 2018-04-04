package com.example.baobang.gameduangua.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by baobang on 4/4/18.
 */

public class Gallery {
    @SerializedName("_id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String photUrl;
    @SerializedName("description")
    private String description;

    public Gallery() {
    }

    public Gallery(String id, String name, String photUrl, String description) {
        this.id = id;
        this.name = name;
        this.photUrl = photUrl;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotUrl() {
        return photUrl;
    }

    public void setPhotUrl(String photUrl) {
        this.photUrl = photUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
