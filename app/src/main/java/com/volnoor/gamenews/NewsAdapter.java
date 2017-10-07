package com.volnoor.gamenews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.volnoor.gamenews.api.NewsData;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Eugene on 06.10.2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsData> news;
    private Context context;

    public NewsAdapter(Context context, List<NewsData> news) {
        this.context = context;
        this.news = news;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_news_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsData n = news.get(position);

        // Name
        holder.name.setText(n.getName());

        // Cover
        Picasso.with(context)
                .load(n.getCover())
                .into(holder.cover);

        // Link
        try {
            URI uri = new URI(n.getLink());
            holder.link.setText(uri.getHost());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // Date
        Date date = new Date(n.getDate() * 1000); // Date is need to be in milliseconds
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        holder.date.setText(dateFormat.format(date));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView cover;
        public TextView link;
        public TextView date;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_news_name);
            cover = itemView.findViewById(R.id.iv_news_cover);
            link = itemView.findViewById(R.id.tv_news_link);
            date = itemView.findViewById(R.id.tv_news_date);
        }
    }
}