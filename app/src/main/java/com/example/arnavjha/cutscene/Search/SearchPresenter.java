package com.example.arnavjha.cutscene.Search;

import android.util.Log;
import android.support.v7.widget.SearchView;

import com.example.arnavjha.cutscene.models.MovieResponse;
import com.example.arnavjha.cutscene.network.NetworkClient;
import com.example.arnavjha.cutscene.network.NetworkInterface;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class SearchPresenter implements SearchPresenterInterface {

    private String TAG = "SearchPresenter";
    SearchViewInterface searchviewInterface;
    public SearchPresenter(SearchViewInterface searchViewInterface) {
        this.searchviewInterface = searchViewInterface;
    }


    @Override
    public void getResultsBasedOnQuery(SearchView searchView) {

        getObservableQuery(searchView)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        if(s.equals("")){
                            return false;
                        }else{
                            return true;
                        }
                    }
                })
                .debounce(2, TimeUnit.SECONDS)
                .distinctUntilChanged()
                .switchMap(new Function<String, ObservableSource<MovieResponse>>() {
                    @Override
                    public Observable<MovieResponse> apply(@NonNull String s) throws Exception {
                        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                                .getMoviesBasedOnQuery("43f4029a324f76d3fc5271d79a5a97aa",s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver());


    }

    private Observable<String> getObservableQuery(SearchView searchView){

        final PublishSubject<String> publishSubject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                publishSubject.onNext(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                publishSubject.onNext(newText);
                return true;
            }
        });

        return publishSubject;
    }

    public DisposableObserver<MovieResponse> getObserver(){
        return new DisposableObserver<MovieResponse>() {

            @Override
            public void onNext(@NonNull MovieResponse MovieResponse) {
                Log.d(TAG,"OnNext"+MovieResponse.getTotalResults());
                searchviewInterface.displayResult(MovieResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                searchviewInterface.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
            }
        };
    }
}
