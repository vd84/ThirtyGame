package com.example.douglashammarstam.thirtygame;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class ResultActivity extends Activity {


    TextView resultText;

    TextView plays;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.totalScoreText);

        resultText.setText(getIntent().getStringExtra("TotalScore"));


        plays = findViewById(R.id.playsText);

        plays.setText(getIntent().getStringExtra("Plays"));





    }



}
