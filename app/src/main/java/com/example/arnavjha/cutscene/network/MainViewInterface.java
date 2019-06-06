package com.example.arnavjha.cutscene.network;

import com.example.arnavjha.cutscene.models.MovieResponse;

public interface MainViewInterface {
    void showToast(String s);

    void displayMovies(MovieResponse movieResponse);

    void displayError(String s);
}
