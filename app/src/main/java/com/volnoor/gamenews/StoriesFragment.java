package com.volnoor.gamenews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volnoor.gamenews.api.NewsClient;
import com.volnoor.gamenews.api.NewsData;
import com.volnoor.gamenews.api.NewsService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoriesFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();

    private List<NewsData> news = new ArrayList<>();
    private NewsAdapter newsAdapter;

    public StoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment StoriesFragment.
     */

    public static StoriesFragment newInstance() {
        return new StoriesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stories, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_news);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        newsAdapter = new NewsAdapter(news);
        recyclerView.setAdapter(newsAdapter);

        loadNews();

        // Inflate the layout for this fragment
        return view;
    }

    private void loadNews() {
        NewsService service = NewsClient.getClient();
        Call<List<NewsData>> loadNewsCall = service.getNewsList();

        loadNewsCall.enqueue(new Callback<List<NewsData>>() {
            @Override
            public void onResponse(Call<List<NewsData>> call, Response<List<NewsData>> response) {
                if (response.isSuccessful()) {
                    news.addAll(response.body());

                    newsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<NewsData>> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }
}