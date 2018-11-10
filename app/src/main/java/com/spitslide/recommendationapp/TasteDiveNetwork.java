package com.spitslide.recommendationapp;


import com.spitslide.recommendationapp.tastedive.Similar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TasteDiveNetwork {

    @GET("api/similar")
    Call<Similar> getReponse(@Query("k") String apiKey, @Query("q") String query);
}
