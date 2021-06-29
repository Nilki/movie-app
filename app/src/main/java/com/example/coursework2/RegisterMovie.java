package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterMovie extends AppCompatActivity {
    EditText title, year, director, actor, rating, review;
    Button saveBtn;
    MovieDatabase movieDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);

        title = findViewById(R.id.title_of_movie);
        year = findViewById(R.id.the_year);
        director = findViewById(R.id.director);
        actor = findViewById(R.id.actor_actresses);
        rating = findViewById(R.id.rating);
        review = findViewById(R.id.review);
        saveBtn = findViewById(R.id.save_btn);
    }

    public void saveMovies(View view) {
        String titleMovie;
        String director;
        String actorActresses;
        String review;
        int year;
        int rating;

        try {
            titleMovie = title.getText().toString();
            director = this.director.getText().toString();
            actorActresses = actor.getText().toString();
            review = this.review.getText().toString();
            year = Integer.parseInt(this.year.getText().toString());
            rating = Integer.parseInt(this.rating.getText().toString());

            movieDatabase = new MovieDatabase(this);

            if (year > 1895){ //check year is > 1895
                if(rating >= 1 && rating <= 10){ //check rating in between 1-10
                    movieDatabase.insertData(title.getText().toString(), //save values in database
                    Integer.parseInt(this.year.getText().toString()),
                    this.director.getText().toString(),
                    actor.getText().toString(),
                    Integer.parseInt(this.rating.getText().toString()),
                    this.review.getText().toString());

                    Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                }else{
                    this.rating.setText("");
                    Toast.makeText(this, "rating not valid", Toast.LENGTH_SHORT).show();
                }
            }else{
                this.year.setText("");
                this.rating.setText("");
            }
        }
        catch (Exception e){
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
        }
    }
}