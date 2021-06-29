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
import java.util.Collection;
import java.util.Collections;

public class DisplayMovies extends AppCompatActivity {

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayList1 = new ArrayList<>();
    CheckBox[] checkBoxes;
    LinearLayout linearLayout;
    MovieDatabase movieDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);
        movieDatabase = new MovieDatabase(this);
        arrayList = movieDatabase.getAll();
        linearLayout = findViewById(R.id.edit_mov_layout);

        checkBoxes = new CheckBox[arrayList.size()];
        Typeface typeface = ResourcesCompat.getFont(this, R.font.carter_one);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 90, 0, 0);
        Collections.sort(arrayList);    //sorting alphabetical order

        for (int i = 0; i < checkBoxes.length; i++) {           //add checkboxes next to movie names
            checkBoxes[i] = new CheckBox(this);
            checkBoxes[i].setId(i);
            checkBoxes[i].setText(arrayList.get(i));
            checkBoxes[i].setTextColor(Color.parseColor("black"));
            checkBoxes[i].setTextSize(20);
            checkBoxes[i].setLayoutParams(params);
            checkBoxes[i].setTypeface(typeface);
            linearLayout.addView(checkBoxes[i]);
        }
        for (int i = 0; i < checkBoxes.length; i++) {
            int finalI = i;
            checkBoxes[i].setOnClickListener(v -> {
                if (checkBoxes[finalI].isChecked()) {
                    arrayList1.add(arrayList.get(finalI));
                }
            });
        }
    }

    public void favoMovie(View view) {
        ArrayList<String> arr = new ArrayList();
        arr = movieDatabase.getAllFav();
        System.out.println(arrayList1);
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arrayList1.size(); j++) {
                if (arr.get(i).equals(arrayList1.get(j))) {
                    arrayList1.remove(arrayList1.get(j));
                }
            }
        }
        for (int i = 0; i < arrayList1.size(); i++) {
            movieDatabase.insertData(arrayList1.get(i));
        }
    }
}