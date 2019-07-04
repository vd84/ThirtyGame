package com.example.douglashammarstam.thirtygame;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

public class Game implements Parcelable {
    private int mData;


    private boolean[] usedAlternatives;

    private int[] playTurns = {3, 6, 9, 12, 15, 18, 21, 24, 27, 30};
    private boolean[] playedTurns;

    private Dice[] dice;
    private int playCount;

    private int rollCount;

    private User user;


    Game() {

        usedAlternatives = new boolean[10];

        playedTurns = new boolean[10];

        dice = new Dice[6];
        playCount = 0;
        rollCount = 0;
        user = new User();


        for (int i = 0; i < 6; i++) {
            dice[i] = new Dice();

        }
    }

    boolean isGameIsOver() {
        return playCount > 2;
    }


    void rollDice(boolean[] notRollIndex) {

        if (canRoll()) {
            incrementRollCount();
            for (int i = 0; i < 6; i++) {

                if (!notRollIndex[i]) {
                    dice[i].setValue(dice[i].roll());
                }
            }
        }
    }

    int play(String userSelectedOption) {


        int pointsGiven = 0;


        playCount++;
        if (userSelectedOption.equals("Low")) {
            pointsGiven = calculateLowSum();


        } else {
            pointsGiven = calculatePossibleMovesForSpecificSum(userSelectedOption);

        }

        return pointsGiven;


    }


    void incrementRollCount() {

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

    Dice[] getDice() {
        return dice;
    }

    public int getPlayCount() {
        return playCount;
    }

    User getUser() {
        return user;
    }

    int calculatePossibleMovesForSpecificSum(String chosenAlternative) {


        int targetSum = 0;
        switch (chosenAlternative) {
            case "4":
                if (!usedAlternatives[1]) {
                    targetSum = 4;
                    usedAlternatives[1] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return -1;
            case "5":
                if (!usedAlternatives[2]) {
                    targetSum = 5;
                    usedAlternatives[2] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return -1;
            case "6":
                if (!usedAlternatives[3]) {
                    targetSum = 6;
                    usedAlternatives[3] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return -1;

            case "7":
                if (!usedAlternatives[4]) {
                    targetSum = 7;
                    usedAlternatives[4] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return -1;
            case "8":
                if (!usedAlternatives[5]) {
                    targetSum = 8;
                    usedAlternatives[5] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return -1;
            case "9":
                if (!usedAlternatives[6]) {
                    targetSum = 9;
                    usedAlternatives[6] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return -1;
            case "10":
                if (!usedAlternatives[7]) {
                    targetSum = 10;
                    usedAlternatives[7] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return -1;
            case "11":
                if (!usedAlternatives[8]) {
                    targetSum = 11;
                    usedAlternatives[8] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return -1;
            case "12":
                if (!usedAlternatives[9]) {
                    targetSum = 12;
                    usedAlternatives[9] = true;
                    break;
                }
                System.out.println("du har redan använt detta alternativ, välj ett annat");
                return -1;
        }

        int pointsGiven = 0;


        Map<Integer, Integer> pairs = new HashMap();
        for (Dice dice : dice) {

            if (dice.getValue() == targetSum) {
                pointsGiven += targetSum;
                dice.setChosenInCalculation(true);
            }
            if (pairs.containsKey(dice.getValue())) {
                if (pairs.get(dice.getValue()) != null) {
                    pointsGiven += givePointsForPlay(dice.getValue(), targetSum - dice.getValue());
                }
            } else if (!pairs.containsValue(dice.getValue())) {
                pairs.put(targetSum - dice.getValue(), dice.getValue());
                dice.setChosenInCalculation(true);
            }
        }
        resetRollCount();
        resetDice();
        user.getPlays().put(chosenAlternative, pointsGiven);
        user.increaseTotalScore(pointsGiven);
        return pointsGiven;


    }

    private void resetDice() {

        for(Dice dice : dice){
            dice.setChosenInCalculation(false);
        }
    }

    int givePointsForPlay(int x, int y) {

        int sum = x + y;

        return sum;

    }

    int calculateLowSum() {

        if (!usedAlternatives[0]) {
            int returnSum = 0;

            for (Dice dice : dice) {
                if (dice.getValue() < 4) {
                    returnSum += dice.getValue();

                }

            }
            resetRollCount();
            System.out.println(returnSum);
            usedAlternatives[0] = true;
            user.getPlays().put("Low", returnSum);
            user.increaseTotalScore(returnSum);
            return returnSum;
        }

        System.out.println("you have already chosen this alternative");
        return -1;

    }

    boolean canRoll() {
        return rollCount <= 2;
    }

    void resetRollCount() {
            rollCount = 0;
    }

    public boolean[] getPlayedTurns() {
        return playedTurns;
    }

    void restart() {

        usedAlternatives = new boolean[10];

        playedTurns = new boolean[10];

        dice = new Dice[6];
        playCount = 0;
        rollCount = 0;
        user = new User();


        for (int i = 0; i < 6; i++) {
            dice[i] = new Dice();

        }    }

    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }

    public static final Parcelable.Creator<Game> CREATOR
            = new Parcelable.Creator<Game>() {
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    private Game(Parcel in) {
        mData = in.readInt();
    }
}



