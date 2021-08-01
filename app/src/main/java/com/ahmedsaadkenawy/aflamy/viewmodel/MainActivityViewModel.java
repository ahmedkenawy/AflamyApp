package com.ahmedsaadkenawy.aflamy.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ahmedsaadkenawy.aflamy.model.Movie;
import com.ahmedsaadkenawy.aflamy.model.MovieDataSource;
import com.ahmedsaadkenawy.aflamy.model.MovieDataSourceFactory;
import com.ahmedsaadkenawy.aflamy.model.MovieRepository;
import com.ahmedsaadkenawy.aflamy.service.MovieDataService;
import com.ahmedsaadkenawy.aflamy.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Movie>> pagedListLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);

        MovieDataService service = RetrofitInstance.getRetrofit();
        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory(service, application);
        movieDataSourceLiveData = movieDataSourceFactory.getMovieMutableLiveData();


        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();
        executor= Executors.newFixedThreadPool(5);
        pagedListLiveData=(new LivePagedListBuilder<Long,Movie>(movieDataSourceFactory,config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<List<Movie>> getMovie() {
        return movieRepository.getMutableLiveData();
    }

    public LiveData<PagedList<Movie>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
