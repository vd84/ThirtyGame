package com.example.douglashammarstam.thirtygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class DiceActivity extends Activity {
    private ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6;
    private ImageView images[] = {imageView1, imageView2, imageView3, imageView4, imageView5, imageView6};
    private CheckBox checkBox1, checkBox12, checkBox3, checkBox4, checkBox5, checkBox6;
    private CheckBox checkBoxes[] = {checkBox1, checkBox12, checkBox3, checkBox4, checkBox5, checkBox6};
    private Button pickButton;
    private Button replayButton;
    private Button resultButton;
    private Intent resultIntent;


    private Game game;
    private Spinner dropdown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        dropdown = findViewById(R.id.dropdown);
        pickButton = findViewById(R.id.ChooseAlternativeBtn);

        String[] items = new String[]{"Low", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        replayButton = findViewById(R.id.replayButton);
        resultButton = findViewById(R.id.resultButton);

        images[0] = (ImageView) findViewById(R.id.dice_icon_1);
        images[1] = (ImageView) findViewById(R.id.dice_icon_2);
        images[2] = (ImageView) findViewById(R.id.dice_icon_3);
        images[3] = (ImageView) findViewById(R.id.dice_icon_4);
        images[4] = (ImageView) findViewById(R.id.dice_icon_5);
        images[5] = (ImageView) findViewById(R.id.dice_icon_6);

        checkBoxes[0] = (CheckBox) findViewById(R.id.checkBox1);
        checkBoxes[1] = (CheckBox) findViewById(R.id.checkBox2);
        checkBoxes[2] = (CheckBox) findViewById(R.id.checkBox3);
        checkBoxes[3] = (CheckBox) findViewById(R.id.checkBox4);
        checkBoxes[4] = (CheckBox) findViewById(R.id.checkBox5);
        checkBoxes[5] = (CheckBox) findViewById(R.id.checkBox6);


        //Skapar usern som ska spela spelet
        game = new Game();


        //Här rullar vi tärningarna
        final Button rollButton = (Button) findViewById(R.id.btnRollDice);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean[] clickedCheckBoxArrayIndex = new boolean[6];
                for (int i = 0; i < 6; i++) {
                    if (checkBoxes[i].isChecked()) {

                        clickedCheckBoxArrayIndex[i] = true;


                    }
                }


                if (!game.canPlay()) {
                    game.rollDice(clickedCheckBoxArrayIndex);

                    for (int i = 0; i < 6; i++) {
                        if (!clickedCheckBoxArrayIndex[i]) {
                            int res = getResources().getIdentifier("grey" + game.getDice()[i].getValue(), "drawable", "com.example.douglashammarstam.thirtygame");
                            images[i].setImageResource(res);
                        }

                    }
                }


            }
        });


        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (game.hasToRoll()) {
                    Toast.makeText(getApplicationContext(), "You hace to roll three times before playing", Toast.LENGTH_SHORT).show();
                    return;

                }


                if (game.canPlay()) {


                    String userSelectedValueDropDown = dropdown.getSelectedItem().toString();


                    int points = game.play(userSelectedValueDropDown);


                    if (points != -1) {
                        Toast.makeText(getApplicationContext(), "You got " + points + " points that round", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "You got " + 0 + " points that round", Toast.LENGTH_SHORT).show();


                    }


                } else {
                    System.out.println("you cant choose alternative yet, please play 3 times");
                }
                if (game.isGameIsOver()) {
                    resultIntent = new Intent(DiceActivity.this, ResultActivity.class);
                    resultIntent.putExtra("TotalScore", String.valueOf(game.getUser().getTotalScore()));
                    resultIntent.putExtra("Plays", game.getUser().convertMapToString());
                    pickButton.setVisibility(View.INVISIBLE);
                    rollButton.setVisibility(View.INVISIBLE);
                    resultButton.setVisibility(View.VISIBLE);

                    startActivity(resultIntent);
                }

            }
        });

        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickButton.setVisibility(View.VISIBLE);
                rollButton.setVisibility(View.VISIBLE);
                resultButton.setVisibility(View.INVISIBLE);


                game.restart();
            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(resultIntent);


            }
        });
    }

}
