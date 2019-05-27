package com.example.arnavjha.cutscene;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.arnavjha.cutscene.Adapter.MoviesAdapter;
import com.example.arnavjha.cutscene.models.MovieResponse;
import com.example.arnavjha.cutscene.network.MainViewInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    RecyclerView rvMovie;
    MoviesAdapter ad;
    MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovie = (RecyclerView)findViewById(R.id.recycle);
        mainPresenter = new MainPresenter(this);
        rvMovie.setLayoutManager(new LinearLayoutManager(this));
        mainPresenter.getMovies();
        ButterKnife.bind(this);



    }

     @Override
    public void displayMovies(MovieResponse movieResponse) {
        if(movieResponse!=null) {

            ad = new MoviesAdapter(movieResponse.getResults(), MainActivity.this);
            rvMovie.setAdapter(ad);
        }
    }



}
