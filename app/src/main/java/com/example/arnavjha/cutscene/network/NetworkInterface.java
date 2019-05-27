package com.example.arnavjha.cutscene.network;

import io.reactivex.Observable;

import com.example.arnavjha.cutscene.models.MovieResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkInterface {

    @GET("discover/movie")
    Observable<MovieResponse> getMovies(@Query("43f4029a324f76d3fc5271d79a5a97aa") String api_key);
}