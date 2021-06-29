package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Favourites extends AppCompatActivity {

    ArrayList<String> favMovies = new ArrayList<>();
    ArrayList<String> selectFavMovie = new ArrayList<>();
    CheckBox[] checkBoxes;
    LinearLayout linearLayout;
    MovieDatabase movieDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        movieDatabase = new MovieDatabase(this);
        favMovies = movieDatabase.getAllFav();
        linearLayout = findViewById(R.id.edit_mov_layout);
        checkBoxes = new CheckBox[favMovies.size()];

        Typeface typeface = ResourcesCompat.getFont(this, R.font.carter_one);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 50, 0,0);
        //use checkBox for select fav movies
        for(int i = 0; i < checkBoxes.length; i++){
            checkBoxes[i] = new CheckBox(this);
            checkBoxes[i].setId(i);
            checkBoxes[i].setText(favMovies.get(i));
            checkBoxes[i].setTextColor(Color.parseColor("#0d1c07"));
            checkBoxes[i].setTextSize(20);
            checkBoxes[i].setChecked(true);
            checkBoxes[i].setLayoutParams(params);
            checkBoxes[i].setTypeface(typeface);
            linearLayout.addView(checkBoxes[i]);
        }
        for(int i = 0; i < checkBoxes.length; i++){
            int finalI = i;
            checkBoxes[i].setOnClickListener(v -> {
                if(!(checkBoxes[finalI].isChecked())){ //!(checkbox)
                    selectFavMovie.add(favMovies.get(finalI));
                }
            });
        }
    }

    public void saveMovies(View view) {
        System.out.println(favMovies);
        System.out.println(selectFavMovie);
        movieDatabase.saveNewFav(selectFavMovie); //save fav movies in database
    }
}


