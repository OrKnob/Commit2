package com.example.arnavjha.cutscene;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.arnavjha.cutscene.Adapter.MoviesAdapter;
import com.example.arnavjha.cutscene.models.MovieResponse;
import com.example.arnavjha.cutscene.network.MainViewInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainViewInterface {
    @BindView(R.id.recycle)
    RecyclerView rvMovie;

    private String TAG = "MainActivity";
    RecyclerView.Adapter adapter;
    MainPresenter mainPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ButterKnife.bind(this);

        setupMVP();
        setupViews();
        getMovieList();



    }

    private void setupMVP() {
        mainPresenter = new MainPresenter(this);
    }

    private void setupViews(){
        rvMovie.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getMovieList() {

        mainPresenter.getMovies();

    }



    @Override
    public void showToast(String str) {
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayMovies(MovieResponse movieResponse) {
        if(movieResponse!=null) {
            Log.d(TAG,movieResponse.getResults().get(1).getTitle());
            adapter = new MoviesAdapter(movieResponse.getResults(), MainActivity.this);
            rvMovie.setAdapter(adapter);
        }else{
            Log.d(TAG,"Movies response null");
        }

    }

    @Override
    public void displayError(String e) {
        showToast(e);

    }
}







