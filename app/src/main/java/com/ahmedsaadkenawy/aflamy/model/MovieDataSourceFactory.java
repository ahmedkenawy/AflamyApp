package com.ahmedsaadkenawy.aflamy.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.ahmedsaadkenawy.aflamy.service.MovieDataService;

import org.jetbrains.annotations.NotNull;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieDataSource movieDataSource;
    private MovieDataService movieDataService;
    private Application application;
    private MutableLiveData<MovieDataSource> movieMutableLiveData;

    public MovieDataSourceFactory(MovieDataService movieDataService, Application application) {
        this.movieDataService = movieDataService;
        this.application = application;
        this.movieMutableLiveData = new MutableLiveData<>();
    }

    @NotNull
    @Override
    public DataSource<Long, Movie> create() {

        movieDataSource = new MovieDataSource(movieDataService, application);
        movieMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }


    public MutableLiveData<MovieDataSource> getMovieMutableLiveData() {
        return movieMutableLiveData;
    }
}
