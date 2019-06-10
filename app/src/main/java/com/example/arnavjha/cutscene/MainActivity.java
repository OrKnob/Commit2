package com.example.arnavjha.cutscene;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.Toast;

import com.example.arnavjha.cutscene.Adapter.MoviesAdapter;
import com.example.arnavjha.cutscene.Search.SearchActivity;
import com.example.arnavjha.cutscene.models.MovieResponse;
import com.example.arnavjha.cutscene.network.MainViewInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainViewInterface {
    @BindView(R.id.recycle)
    RecyclerView rvMovie;

   // @BindView(R.id.toolbar1)
    //Toolbar toolbar;

    private String TAG = "MainActivity";
    RecyclerView.Adapter adapter;
    MainPresenter mainPresenter;
    //Boolean isScrolling = false;

    //int currentItems,totalItems,scrollOutItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupMVP();
        setupViews();
        getMovieList();


        //for infinite recycler view

        //rvMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //@Override
            //public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
               // super.onScrollStateChanged(recyclerView, newState);
                //if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                //    isScrolling = true;
               // }

           // }

            //@Override
            //public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
               // super.onScrolled(recyclerView, dx, dy);
               // currentItems = manager.getChildCount();
               // totalItems = manager.getItemCount();
               // scrollOutItems = manager.findFirstVisibleItemPosition();

               // if (isScrolling == true &&(currentItems + scrollOutItems == totalItems)) {
                 //   isScrolling = false;
                //    fetchMovies();
               // }
           // }
        // });

    }

    //private void fetchMovies() {
      //  new Handler().postDelayed(new Runnable() {
        //    @Override
          //  public void run() {
            //for (int i = 0 ; i<5;i++){
              //  mainPresenter.getMovies();
                //adapter.notifyDataSetChanged();
            //}
            //}
        //}, 3000);
    //}

    private void setupViews(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvMovie.setLayoutManager(linearLayoutManager);
        //setSupportActionBar(toolbar);
    }

    private void setupMVP(){
        mainPresenter = new MainPresenter(this);
    }

    private void getMovieList(){
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.search){
            Intent i = new Intent(this,SearchActivity.class);
            startActivity(i);

        }

        return super.onOptionsItemSelected(item);
    }
}








