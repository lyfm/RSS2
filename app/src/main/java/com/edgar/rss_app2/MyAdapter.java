package com.edgar.rss_app2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<News> feedItems;
    Context context;
    String link;
    public static final String KEY = "key";

    public MyAdapter(Context context, ArrayList<News> feedItems) {
        this.feedItems = feedItems;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(KEY,  "onCreateViewHolder");

        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.d(KEY,  "onBindViewHolder");

        final News current = feedItems.get(position);
        link = current.getLink();
        holder.title.setText(context.getString(R.string.title) + current.getTitle());
        holder.pubDate.setText(context.getString(R.string.publication_date) + current.getPubDate());
        holder.author.setText(context.getString(R.string.author) + current.getAuthor());
        Picasso.with(context).load(current.getImage()).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebPage.class);
                intent.putExtra(KEY, link);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView title, pubDate, author;
        ImageView image;

        public MyViewHolder(final View itemView) {

            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            pubDate = (TextView) itemView.findViewById(R.id.pubDate);
            author = (TextView) itemView.findViewById(R.id.author);
            image = (ImageView) itemView.findViewById(R.id.image);
            cardView = (CardView) itemView.findViewById(R.id.card_view);

        }
    }
    }

