package com.example.arnavjha.cutscene.Adapter;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arnavjha.cutscene.R;
import com.example.arnavjha.cutscene.models.Result;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder> {
    List<Result> movieList;
    Context ctx;

    public MoviesAdapter(List<Result> movieList, Context ct) {
        this.ctx = ct;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MoviesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(ctx).inflate(R.layout.rows, viewGroup, false);
        MoviesHolder mh = new MoviesHolder(v);
        return mh;

    }

    @Override
    public void onBindViewHolder(@NonNull MoviesHolder moviesHolder, int i) {
        final MoviesHolder holder = moviesHolder;
        holder.tvTitle.setText(movieList.get(i).getTitle());
        holder.tvReleaseDate.setText(movieList.get(i).getReleaseDate());
        Glide.with(ctx).load("https://image.tmdb.org/t/p/w500/" + movieList.get(i).getPosterPath()).into(holder.ivMovie);

    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MoviesHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvReleaseDate;
        ImageView ivMovie;

        public MoviesHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            tvReleaseDate = (TextView)itemView.findViewById(R.id.tvReleaseDate);
            ivMovie = (ImageView)itemView.findViewById(R.id.ivMovie);
        }


    }
}

