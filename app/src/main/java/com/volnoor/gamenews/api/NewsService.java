package com.volnoor.gamenews.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Eugene on 07.10.2017.
 */

public interface NewsService {

    @GET("/api/v1/feedNews?lang=en&count=10&sources=7,19,13,5,15,16,12,9,10012,10010,10013,10014,10019,10018,10011&feedLineId=5")
    Call<List<NewsData>> getNewsList();
}