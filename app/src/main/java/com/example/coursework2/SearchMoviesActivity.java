package com.example.coursework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.Paths;
import java.util.ArrayList;

public class SearchMoviesActivity extends AppCompatActivity {
    EditText search;
    Button lookup;
    String data;
    LinearLayout title, director, cast;
    MovieDatabase movieDatabase;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayList1 = new ArrayList<>();
    ArrayList<String> arrayList2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        search = findViewById(R.id.search_movie_view);
        lookup = findViewById(R.id.lookup_btn);
        title = findViewById(R.id.titleLayout);
        director = findViewById(R.id.directorLayout);
        cast = findViewById(R.id.castLayout);
        movieDatabase = new MovieDatabase(this);
        arrayList = movieDatabase.getAll();
    }

    public void Lookup(View view) {
        data = search.getText().toString();
//display values
        if(!data.equals("")){
            arrayList = movieDatabase.getTitle(data);
            arrayList1 = movieDatabase.getDirector(data);
            arrayList2 = movieDatabase.getCast(data);

            if(arrayList.isEmpty()){
                title.removeAllViews();
                TextView textView = new TextView(this);
                textView.setText("Result Not Found");
                textView.setTextSize(24);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.parseColor("#ffffff"));
                title.addView(textView);
            }else{
                for(int i = 0;  i< arrayList.size(); i++){
                    title.removeAllViews();
                    TextView textView = new TextView(this);
                    textView.setText(arrayList.get(i) + "\n");
                    textView.setTextSize(24);
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextColor(Color.parseColor("#ffffff"));
                    title.addView(textView);
                }
            }
            if(arrayList1.isEmpty()){
                director.removeAllViews();
                TextView textView = new TextView(this);
                textView.setText("Result Not Found");
                textView.setTextSize(24);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.parseColor("#ffffff"));
                director.addView(textView);
            }else {
                for (int i = 0; i < arrayList1.size(); i++) {
                    director.removeAllViews();
                    TextView textView = new TextView(this);
                    textView.setText(arrayList1.get(i) + "\n");
                    textView.setTextSize(24);
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextColor(Color.parseColor("#ffffff"));
                    director.addView(textView);
                }
            }
            if(arrayList2.isEmpty()){
                cast.removeAllViews();
                TextView textView = new TextView(this);
                textView.setText("Result Not Found");
                textView.setTextSize(24);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.parseColor("#ffffff"));
                cast.addView(textView);
            }else {
                for (int i = 0; i < arrayList2.size(); i++) {
                    cast.removeAllViews();
                    TextView textView = new TextView(this);
                    textView.setText(arrayList2.get(i) + "\n");
                    textView.setTextSize(24);
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextColor(Color.parseColor("#ffffff"));
                    cast.addView(textView);
                }
            }
        }
    }
}
