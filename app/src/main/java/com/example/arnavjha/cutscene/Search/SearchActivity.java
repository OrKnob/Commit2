package com.example.arnavjha.cutscene.Search;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;

import com.example.arnavjha.cutscene.Adapter.MoviesAdapter;
import com.example.arnavjha.cutscene.R;
import com.example.arnavjha.cutscene.models.MovieResponse;
import com.example.arnavjha.cutscene.network.MainViewInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchViewInterface {

    @BindView(R.id.recyclePart2)
    RecyclerView rvQueryResult;
    //@BindView(R.id.toolbar)
    //Toolbar toolbar;

    private SearchView searchView;
    SearchPresenter searchPresenter;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setupViews();
        setupMVP();
    }

    private void setupViews() {
        rvQueryResult.setLayoutManager(new LinearLayoutManager(this));
        //setSupportActionBar(toolbar);
    }

    private void setupMVP(){
         searchPresenter = new SearchPresenter(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search,menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Enter Movie name..");

        searchPresenter.getResultsBasedOnQuery(searchView);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_search){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showToast(String str) {
        Toast.makeText(SearchActivity.this,str, Toast.LENGTH_LONG).show();

    }

    @Override
    public void displayResult(MovieResponse movieResponse) {
        adapter = new MoviesAdapter(movieResponse.getResults(),SearchActivity.this);
        rvQueryResult.setAdapter(adapter);
    }

    @Override
    public void displayError(String s) {
        showToast(s);
    }
}
