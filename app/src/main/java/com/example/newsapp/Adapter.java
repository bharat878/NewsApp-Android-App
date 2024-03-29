package com.example.newsapp;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.newsapp.m.Article;

import java.util.HashMap;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    public static final String IMAGE = "IMAGE";
    public static final String TITLE = "TITLE";
    public static final String DESC = "DESC";
    public static final String AUTHOR = "AUTHOR";
    public static final String PUBLISHEDAT = "PUBLISHEDAT";
    public static final String SOURCE = "SOURCE";
    public static final String HASH_EXTRA = "HASH_EXTRA";

    private List<Article> articles;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public Adapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new MyViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, final int i) {
        final MyViewHolder holder = viewHolder;
        final Article model = articles.get(i);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(utils.getRandomDrawbleColor());
        requestOptions.error(utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        Glide.with(context)
                .load(model.getUrlToImage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);

        holder.title.setText(model.getTitle());
        holder.desc.setText(model.getDescription());
        holder.source.setText(model.getSource().getId());
//        if (utils.DateToTimeFormat(model.getPublishedAt()) != null) {
//            holder.time.setText(" \u2022 " + utils.DateToTimeFormat(model.getPublishedAt()));
//        }
        holder.publishedAt.setText(utils.DateFormat(model.getPublishedAt()));
        holder.author.setText(model.getAuthor());


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(TITLE, model.getTitle());
                hashMap.put(IMAGE, model.getUrlToImage());
                hashMap.put(DESC, model.getDescription());
                hashMap.put(AUTHOR, model.getAuthor());
                hashMap.put(PUBLISHEDAT, model.getPublishedAt());
               // hashMap.put(SOURCE, model.getSource());

                Intent intent = new Intent(context, NewsDetails.class);
                intent.putExtra(HASH_EXTRA,hashMap);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, desc, author, publishedAt, source, time;
        ImageView imageView;
        ProgressBar progressBar;
        OnItemClickListener onItemClickListener;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            author = itemView.findViewById(R.id.author);
            publishedAt = itemView.findViewById(R.id.publishedAt);
            source = itemView.findViewById(R.id.source);
            imageView = itemView.findViewById(R.id.img);
            progressBar = itemView.findViewById(R.id.progress_load_photo);

            this.onItemClickListener = this.onItemClickListener;

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
