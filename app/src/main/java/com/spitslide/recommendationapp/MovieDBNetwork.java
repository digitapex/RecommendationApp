package com.spitslide.recommendationapp;


import com.spitslide.recommendationapp.moviedb.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDBNetwork {

    @GET("3/search/movie")
    Call<Result> getResponse(@Query("api_key") String apiKey, @Query("query") String query);


}