package com.nd.popularmovies.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerData {
    public static List<TrailerData> trailerDataList = null;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("type")
    @Expose
    private String type;

    public static List<TrailerData> gettList() {
        return trailerDataList;
    }

    public static void settList(List<TrailerData> tList) {
        if (tList != null)
            TrailerData.trailerDataList = tList;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
