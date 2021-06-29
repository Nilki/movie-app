package com.example.coursework2;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.content.res.ResourcesCompat;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity {
    ArrayList<String> arrayList = new ArrayList<>();
    Button[] buttons;
    LinearLayout linearLayout;
    MovieDatabase movieDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        movieDatabase = new MovieDatabase(this);
        arrayList = movieDatabase.getAll();
        linearLayout = findViewById(R.id.rating_layout);

        buttons = new Button[arrayList.size()];
        Typeface typeface = ResourcesCompat.getFont(this, R.font.carter_one);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 90, 0, 0);

        for (int i = 0; i < buttons.length; i++) {      //use button for display all names
            buttons[i] = new Button(this);
            buttons[i].setId(i);
            buttons[i].setText(arrayList.get(i));
            buttons[i].setBackgroundColor(Color.parseColor("black"));
            buttons[i].setTextColor(Color.parseColor("white"));
            buttons[i].setBackgroundColor(Color.parseColor("#59FFEB3B"));
            buttons[i].setTextSize(14);
            buttons[i].setLayoutParams(params);
            buttons[i].setTypeface(typeface);
            linearLayout.addView(buttons[i]);
        }
        for (int i = 0; i < buttons.length; i++) {
            int finalI = i;
            int finalI1 = i;
            buttons[i].setOnClickListener(v -> {
                Intent j = new Intent(this, RatingActivityTwo.class);
                j.putExtra("DETAILS" , buttons[finalI1].getText());
                startActivity(j);
            });
        }
    }

    public void FindMovie(View view) {
    }
}