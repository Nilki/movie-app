package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RatingActivityTwo extends AppCompatActivity {
    String movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_two);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            movie = extras.getString("DETAILS");
        }
    }
}