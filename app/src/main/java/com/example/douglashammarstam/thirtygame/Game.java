package com.example.douglashammarstam.thirtygame;

import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Game {



    private boolean[] usedAlternatives = new boolean[10];

    private int[] playTurns = {3,6,9,12,15,18,21,24,27,30};
    private boolean[] playedTurns = new boolean[10];

    private Dice[] dice = new Dice[6];
    private int playCount = 0;

    private int rollCount = 0;

    private User user;


    //one player


    public Game() {
        for (int i = 0; i < 6; i++) {
            dice[i] = new Dice();

        }
    }

    public boolean isGameIsOver() {
        return playCount > 9 && !canPlay();
    }

    public boolean canPlay() {
        for (int i : playTurns){
            if (rollCount == i)
                return true;
        }
        return false;

    }


    public void rollDice(boolean[] notRollIndex) {
        if (canPlay()) {
            System.out.println("you have to choose alternative, please do so");
            return;
        }
        incrementRollCount();
        for (int i = 0; i < 6; i++) {

            if (!notRollIndex[i]) {
                dice[i].setValue(dice[i].roll());
            }
        }
    }

    public int play(String userSelectedOption) {

        int pointsGiven = 0;

        if(!canPlay()){

            return -1;
        }

        playedTurns[]
        playCount++;
        if (userSelectedOption.equals("Low")){
            pointsGiven = calculateLowSum();


        } else {
            pointsGiven = calculatePossibleMovesForSpecificSum(userSelectedOption);

        }

        return pointsGiven;


    }


    public void incrementRollCount() {
        rollCount++;
    }

    public int[] getPlayTurns() {
        return playTurns;
    }

    public void setPlayTurns(int[] playTurns) {
        this.playTurns = playTurns;
    }

    public int getRollCount() {
        return rollCount;
    }

    public void setRollCount(int rollCount) {
        this.rollCount = rollCount;
    }

    public boolean[] getUsedAlternatives() {
        return usedAlternatives;
    }

    public Dice[] getDice() {
        return dice;
    }

    public int getPlayCount() {
        return playCount;
    }

    public User getUser() {
        return user;
    }

    public int calculatePossibleMovesForSpecificSum(String chosenAlternative) {




        int targetSum = 0;
        switch (chosenAlternative) {
            case "4":
                if (!usedAlternatives[1]) {
                    targetSum = 4;
                    usedAlternatives[1] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return-1;
            case "5":
                if (!usedAlternatives[2]) {
                    targetSum = 5;
                    usedAlternatives[2] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return-1;
            case "6":
                if (!usedAlternatives[3]) {
                    targetSum = 6;
                    usedAlternatives[3] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return-1;

            case "7":
                if (!usedAlternatives[4]) {
                    targetSum = 7;
                    usedAlternatives[4] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return-1;
            case "8":
                if (!usedAlternatives[5]) {
                    targetSum = 8;
                    usedAlternatives[5] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return-1;
            case "9":
                if (!usedAlternatives[6]) {
                    targetSum = 9;
                    usedAlternatives[6] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return-1;
            case "10":
                if (!usedAlternatives[7]) {
                    targetSum = 10;
                    usedAlternatives[7] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return-1;
            case "11":
                if (!usedAlternatives[8]) {
                    targetSum = 11;
                    usedAlternatives[8] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return-1;
            case "12":
                if (!usedAlternatives[9]) {
                    targetSum = 12;
                    usedAlternatives[9] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return-1;
        }


        Map<Integer, Integer> pairs = new HashMap();
        for (Dice dice : dice) {
            if (pairs.containsKey(dice.getValue())) {
                if (pairs.get(dice.getValue()) != null) {
                    return givePointsForPlay(dice.getValue(), targetSum - dice.getValue());
                }
                pairs.put(targetSum - dice.getValue(), null);
            } else if (!pairs.containsValue(dice.getValue())) {
                pairs.put(targetSum - dice.getValue(), dice.getValue());
            }
        }
        return -1;


    }

    public int givePointsForPlay(int x, int y) {

        int sum = x + y;
        System.out.println("du får " + sum + " poäng för detta drag");

        return  sum;

    }

    public int calculateLowSum() {

        if (!usedAlternatives[0]) {
            int returnSum = 0;

            for (Dice dice : dice) {
                if (dice.getValue() < 4) {
                    returnSum += dice.getValue();

                }

            }
            System.out.println(returnSum);
            usedAlternatives[0] = true;
            return returnSum;
        }
        System.out.println("you have already chosen this alternative");
        return -1;
    }



}



