package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditActivity2 extends AppCompatActivity {
    LinearLayout rating, updateRate;
    ImageView[] editingView;
    TextView title, year, director, actors, review;
    EditText titleUpdate, yearUpdate, directorUpdate, actorsUpdate, reviewUpdate;
    String movie;
    String updatedTitle, updatedYear, updatedDirector, updatedActors, updatedReview;
    ArrayList<String> ary = new ArrayList<>();
    ArrayList<String> allFavMovie = new ArrayList<>();
    MovieDatabase movieDatabase =  new MovieDatabase(this);
    int currentActiveRating;
    boolean favouriteStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit2);
        displayDetails();
    }
    public void displayDetails(){
        rating = findViewById(R.id.rating_layout);
        updateRate = findViewById(R.id.update_rate);
        title = findViewById(R.id.title_db);
        year = findViewById(R.id.year_db);
        director = findViewById(R.id.director_db);
        actors = findViewById(R.id.actors_db);
        review = findViewById(R.id.review_db);

        //updated values
        titleUpdate = findViewById(R.id.Title_update);
        yearUpdate = findViewById(R.id.year_update);
        directorUpdate = findViewById(R.id.Director_update);
        actorsUpdate = findViewById(R.id.Actor_update);
        reviewUpdate= findViewById(R.id.review_update);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            movie = extras.getString("DETAILS");
        }
        System.out.println(movie);
        ary = movieDatabase.findDet(movie);

        // update ratings
        ratingStars(Integer.parseInt(ary.get(4)));
        currentActiveRating = Integer.parseInt(ary.get(4));
        ratingStarsUpdate(currentActiveRating);

        //get values and make arrayList
        title.setText(ary.get(0));
        year.setText(ary.get(1));
        director.setText(ary.get(2));
        actors.setText(ary.get(3));
        review.setText(ary.get(5));
        System.out.println(ary);

        //update new values
        titleUpdate.setText(ary.get(0));
        yearUpdate.setText(ary.get(1));
        directorUpdate.setText(ary.get(2));
        actorsUpdate.setText(ary.get(3));
        reviewUpdate.setText(ary.get(5));

        // Radio Button for movie
        RadioGroup group = findViewById(R.id.fav_radio);
        group.setOnCheckedChangeListener((group1, checkedId) -> {

            switch (checkedId) {
                case R.id.radio_fav:
                    favouriteStatus = true;
                    Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radio_notfav:
                    favouriteStatus = false;
                    Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(this, "Movie is not a favourite", Toast.LENGTH_SHORT).show();
                    break;
            }
        });

    }
    //current rating display
    private void ratingStars(int activeStars) {
        editingView = new ImageView[10];
        for (int i = 0; i < 10; i++) {
            editingView[i] = new ImageView(this);
            editingView[i].setId(i);

            if (i < activeStars) {
                editingView[i].setBackgroundResource(R.drawable.yellow1);
            } else {
                editingView[i].setBackgroundResource(R.drawable.white1);
            }
            rating.addView(editingView[i]);
        }
    }

    private void ratingStarsUpdate(int activeStars) {
        editingView = new ImageView[10];
        for (int i = 0; i < 10; i++) {
            editingView[i] = new ImageView(this);
            editingView[i].setId(i);
            editingView[i].isClickable();

            if (i < activeStars) {
                editingView[i].setBackgroundResource(R.drawable.yellow1);
            } else {
                editingView[i].setBackgroundResource(R.drawable.white1);
            }
            updateRate.addView(editingView[i]);
            //user can update rating
            int finalI = i;
            editingView[i].setOnClickListener(v -> {
                updateRate.removeAllViews();
                currentActiveRating = finalI +1;
                ratingStarsUpdate(currentActiveRating);
            });
        }
    }

    public void Update(View view) {
        ArrayList<String> arrayList = new ArrayList<>();
        updatedTitle = titleUpdate.getText().toString();
        updatedYear = yearUpdate.getText().toString();
        updatedDirector = directorUpdate.getText().toString();
        updatedActors = actorsUpdate.getText().toString();
        updatedReview = reviewUpdate.getText().toString();

        int year = Integer.parseInt(updatedYear);
        if(year>1895){
            arrayList.add(updatedTitle);
            arrayList.add(String.valueOf(year));
            arrayList.add(updatedDirector);
            arrayList.add(updatedActors);
            arrayList.add(String.valueOf(currentActiveRating));
            arrayList.add(updatedReview);
            if(movieDatabase.updateMovieDetails(arrayList, movie)){
                Toast.makeText(this, "data updated successfully ", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Year not valid", Toast.LENGTH_SHORT).show();
            yearUpdate.setText("");
        }

        allFavMovie = movieDatabase.getAllFav();

        if (!(allFavMovie.contains(movie)) && favouriteStatus){
            movieDatabase.insertData(movie);
        }
        if (allFavMovie.contains(movie) && !favouriteStatus){
            ArrayList<String> addFavList = new ArrayList<>();
            addFavList.add(movie);
            movieDatabase.saveNewFav(addFavList);
        }

    }


}