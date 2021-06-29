package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // to get action bar settings
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);

    }
    //register movie
    public void registerMovie(View view) {
        Intent i = new Intent(this, RegisterMovie.class);
        startActivity(i);
    }
    //display movie
    public void displayMovie(View view) {
        Intent i = new Intent(this, DisplayMovies.class);
        startActivity(i);
    }
    //favourites
    public void favourites(View view) {
        Intent i = new Intent(this, Favourites.class);
        startActivity(i);
    }
    //edit movie
    public void editMovies(View view) {
        Intent i = new Intent(this, EditMoviesActivity.class);
        startActivity(i);
    }
    //rate
    public void rating(View view) {
        Intent i = new Intent(this, RatingActivity.class);
        startActivity(i);
    }
    //search
    public void searchMovie(View view) {
        Intent i = new Intent(this, SearchMoviesActivity.class);
        startActivity(i);
    }
}