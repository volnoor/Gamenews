package com.volnoor.gamenews.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eugene on 07.10.2017.
 */

public class NewsClient {

    private static NewsService service;

    public static NewsService getClient() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://owledge.ru")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(NewsService.class);
        }

        return service;
    }
}