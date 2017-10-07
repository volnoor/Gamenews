package com.volnoor.gamenews.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eugene on 06.10.2017.
 */

public class NewsData {
    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("date")
    @Expose
    private long date;

    @SerializedName("cover")
    @Expose
    private String cover;

    public void setLink(String link) {
        this.link = link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public long getDate() {
        return date;
    }

    public String getCover() {
        return cover;
    }
}