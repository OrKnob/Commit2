package com.example.arnavjha.cutscene.network;

import com.example.arnavjha.cutscene.models.MovieResponse;

public interface MainViewInterface {
    void showToast(String str);

    void displayMovies(MovieResponse movieResponse);

    void displayError(String e);
}
