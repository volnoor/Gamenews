package com.volnoor.gamenews.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eugene on 06.10.2017.
 */

public class NewsData implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(link);
        parcel.writeString(name);
        parcel.writeLong(date);
        parcel.writeString(cover);
    }

    protected NewsData(Parcel in) {
        link = in.readString();
        name = in.readString();
        date = in.readLong();
        cover = in.readString();
    }

    public static final Creator<NewsData> CREATOR = new Creator<NewsData>() {
        @Override
        public NewsData createFromParcel(Parcel in) {
            return new NewsData(in);
        }

        @Override
        public NewsData[] newArray(int size) {
            return new NewsData[size];
        }
    };
}