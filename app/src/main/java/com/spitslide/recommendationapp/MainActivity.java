package com.spitslide.recommendationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String SEARCH_TERM = "com.spitslide.recommendationapp.SEARCH_TERM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSearch(View view) {
        Intent intent = new Intent(this, SuggestionsActivity.class);
        String searchTerm = ((EditText)findViewById(R.id.edit_text)).getText().toString();
        intent.putExtra(SEARCH_TERM, searchTerm);
        startActivity(intent);

    }


}

