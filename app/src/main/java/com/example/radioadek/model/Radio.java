package com.example.radioadek.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by Monty on 14.05.2015.
 */
public class Radio {
    private String stationName;
    private String description;
    private Bitmap cover;
    private String urlStream;

    public Radio(String name, String description, Bitmap cover, String urlStream) {
        this.stationName = name;
        this.description = description;
        this.cover = cover;
        this.urlStream = urlStream;
    }

    /*Getters and setters*/

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getCover() {
        return cover;
    }

    public void setCover(Bitmap cover) {
        this.cover = cover;
    }

    public String getUrlStream() {
        return urlStream;
    }

    public void setUrlStream(String urlStream) {
        this.urlStream = urlStream;
    }
}
