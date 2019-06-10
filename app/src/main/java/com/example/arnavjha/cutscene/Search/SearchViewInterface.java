package com.example.arnavjha.cutscene.Search;

import com.example.arnavjha.cutscene.models.MovieResponse;

public interface SearchViewInterface {


        void showToast(String str);
        void displayResult(MovieResponse movieResponse);
        void displayError(String s);
    }

