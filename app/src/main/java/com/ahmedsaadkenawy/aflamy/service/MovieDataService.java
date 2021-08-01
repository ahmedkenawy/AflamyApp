package com.ahmedsaadkenawy.aflamy.service;

import com.ahmedsaadkenawy.aflamy.model.MovieDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {

    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovie(@Query("api_key") String api_key);


    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovieWithPaging(@Query("api_key") String api_key, @Query("page") long page);
}
