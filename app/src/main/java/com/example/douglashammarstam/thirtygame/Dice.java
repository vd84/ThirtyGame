package com.example.douglashammarstam.thirtygame;

import java.util.Random;

public class Dice {

    private int value;
    private int timesRolled;
    private boolean chosenInCalculation;




    public int roll() {



        Random rnd = new Random();
        int randomValue = rnd.nextInt(6) + 1;
        value = randomValue;
        timesRolled++;
        return randomValue;


    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getTimesRolled() {
        return timesRolled;
    }

    public void setTimesRolled(int timesRolled) {
        this.timesRolled = timesRolled;
    }

    public boolean isChosenInCalculation() {
        return chosenInCalculation;
    }

    public void setChosenInCalculation(boolean chosenInCalculation) {
        this.chosenInCalculation = chosenInCalculation;
    }
}
