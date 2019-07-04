package com.example.douglashammarstam.thirtygame;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class ResultActivity extends Activity {


    TextView resultText;

    TextView plays;


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("TotalScore", resultText.getText().toString());
        outState.putString("Plays", resultText.getText().toString());


        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.totalScoreText);
        plays = findViewById(R.id.playsText);


        if (savedInstanceState != null){
            resultText.setText(savedInstanceState.getString("TotalScore"));
            plays.setText(savedInstanceState.getString("Plays"));

        }


        resultText.setText(getIntent().getStringExtra("TotalScore"));
        plays.setText(getIntent().getStringExtra("Plays"));









    }



}
