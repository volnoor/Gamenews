package com.volnoor.gamenews;

/**
 * Created by Eugene on 06.10.2017.
 */

public class NewsData {
    private String link;
    private String name;
    private long date;
    private String cover;

    public NewsData(String link, String name, long date, String cover) {
        this.link = link;
        this.name = name;
        this.date = date;
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