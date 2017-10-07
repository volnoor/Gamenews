package com.volnoor.gamenews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.volnoor.gamenews.adapter.NewsAdapter;
import com.volnoor.gamenews.adapter.TopNewsAdapter;
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

    private List<NewsData> topNews = new ArrayList<>();
    private TopNewsAdapter topNewsAdapter;

    private LinearLayout llPageIndicator;
    private int pageIndicatorPosition;

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

        // Top news
        RecyclerView rvTopNews = view.findViewById(R.id.rv_top_news);

        LinearLayoutManager topNewsLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTopNews.setLayoutManager(topNewsLayoutManager);

        topNewsAdapter = new TopNewsAdapter(getContext(), topNews);
        rvTopNews.setAdapter(topNewsAdapter);

        // add pager behavior
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvTopNews);

        llPageIndicator = view.findViewById(R.id.ll_page_indicator);

        rvTopNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int pos = ((LinearLayoutManager) (recyclerView.getLayoutManager())).findFirstVisibleItemPosition();
                updatePageIndicator(pos);
                Log.d("TAG", "" + pos);
            }
        });


        // News
        RecyclerView rvNews = view.findViewById(R.id.rv_news);

        RecyclerView.LayoutManager newsLayoutManager = new LinearLayoutManager(getContext());
        rvNews.setLayoutManager(newsLayoutManager);

        newsAdapter = new NewsAdapter(getContext(), news);
        rvNews.setAdapter(newsAdapter);

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
                    List<NewsData> newsDataList = response.body();

                    news.addAll(newsDataList);
                    topNews.addAll(newsDataList);

                    newsAdapter.notifyDataSetChanged();
                    topNewsAdapter.notifyDataSetChanged();

                    setupPageIndicator(newsDataList.size());
                }
            }

            @Override
            public void onFailure(Call<List<NewsData>> call, Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }

    private void updatePageIndicator(int position) {
        ((ImageView) llPageIndicator.getChildAt(pageIndicatorPosition)).setImageResource(R.drawable.indicator); // Deselect
        ((ImageView) llPageIndicator.getChildAt(position)).setImageResource(R.drawable.indicator_selected); // Select

        pageIndicatorPosition = position;
    }

    private void setupPageIndicator(int numberOfPages) {
        for (int i = 0; i < numberOfPages; i++) {
            ImageView image = new ImageView(getContext());
            image.setImageResource(R.drawable.indicator);

            int size = getResources().getDimensionPixelSize(R.dimen.indicator_size);
            int margin = getResources().getDimensionPixelSize(R.dimen.indicator_margin);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
            layoutParams.setMargins(margin, margin, margin, margin);
            image.setLayoutParams(layoutParams);

            llPageIndicator.addView(image);
        }

        ((ImageView) llPageIndicator.getChildAt(0)).setImageResource(R.drawable.indicator_selected);
    }
}