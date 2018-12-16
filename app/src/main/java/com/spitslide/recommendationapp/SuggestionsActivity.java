package com.spitslide.recommendationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.spitslide.recommendationapp.moviedb.Result;
import com.spitslide.recommendationapp.tastedive.Similar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SuggestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        List<String> data = new ArrayList<>();
        data.add("aaaa");
        data.add("bbbb");
        data.add("cccc");
        SuggestionsAdapter suggestionsAdapter = new SuggestionsAdapter(data);
        recyclerView.setAdapter(suggestionsAdapter);


        Intent intent = getIntent();
        String searchTerm = intent.getStringExtra(MainActivity.SEARCH_TERM);
        tasteDiveConnection(searchTerm);
    }


    private void tasteDiveConnection(String searchTerm) {
        // this needed because there's a bug on pre-Lollipop for TLS
        // https://stackoverflow.com/a/50640113/9702500
        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
                .supportsTlsExtensions(true)
                .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
                        CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
                        CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA)
                .build();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectionSpecs(Collections.singletonList(spec))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tastedive.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        TasteDiveNetwork tasteDiveNetwork = retrofit.create(TasteDiveNetwork.class);
        Call<Similar> call = tasteDiveNetwork.getReponse(BuildConfig.TASTEDIVE_API_KEY, searchTerm);
        call.enqueue(new Callback<Similar>() {
            @Override
            public void onResponse(Call<Similar> call, Response<Similar> response) {
                Log.d("MY", "reponse: " + response.body().getSimilar().getResults().get(3).getName());
            }

            @Override
            public void onFailure(Call<Similar> call, Throwable t) {

            }
        });
    }

    private void movieDbConnection(String movieName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieDBNetwork movieDBNetwork = retrofit.create(MovieDBNetwork.class);
        Call<Result> call = movieDBNetwork.getResponse(BuildConfig.MOVIEDB_API_KEY, movieName);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
}
