package com.volnoor.gamenews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class StoriesFragment extends Fragment {

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

        List<NewsData> news = new ArrayList<>();
        news.add(new NewsData("link", "name", System.currentTimeMillis(), "cover"));
        news.add(new NewsData("link1", "name1", System.currentTimeMillis(), "cover1"));

        NewsAdapter adapter = new NewsAdapter(news);
        recyclerView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }
}