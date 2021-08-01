package com.ahmedsaadkenawy.aflamy.model;


import android.app.Application;

import androidx.paging.PageKeyedDataSource;

import com.ahmedsaadkenawy.aflamy.R;
import com.ahmedsaadkenawy.aflamy.service.MovieDataService;
import com.ahmedsaadkenawy.aflamy.service.RetrofitInstance;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {
    private MovieDataService dataService;
    private Application application;

    public MovieDataSource(MovieDataService dataService, Application application) {
        this.dataService = dataService;
        this.application = application;
    }

    @Override
    public void loadAfter(@NotNull LoadParams<Long> loadParams, @NotNull LoadCallback<Long, Movie> loadCallback) {

        dataService = RetrofitInstance.getRetrofit();
        Call<MovieDBResponse> call = dataService.getPopularMovieWithPaging(application.getApplicationContext().getString(R.string.api_key), loadParams.key);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();
                ArrayList<Movie> movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                loadCallback.onResult(movies, loadParams.key + 1);

            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NotNull LoadParams<Long> loadParams, @NotNull LoadCallback<Long, Movie> loadCallback) {

    }

    @Override
    public void loadInitial(@NotNull LoadInitialParams<Long> loadInitialParams, @NotNull LoadInitialCallback<Long, Movie> loadInitialCallback) {
        dataService = RetrofitInstance.getRetrofit();
        Call<MovieDBResponse> call = dataService.getPopularMovieWithPaging(application.getApplicationContext().getString(R.string.api_key), 1);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse movieDBResponse = response.body();
                ArrayList<Movie> movies = (ArrayList<Movie>) movieDBResponse.getMovies();
                loadInitialCallback.onResult(movies, null, (long) 2);


            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
    }
}
