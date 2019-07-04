package com.example.douglashammarstam.thirtygame;


import java.util.HashMap;
import java.util.Map;

public class User {


    private int totalScore;

    private Map<String, Integer> plays = new HashMap<>();





    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public Map<String, Integer> getPlays() {
        return plays;
    }

    public void setPlays(Map<String, Integer> plays) {
        this.plays = plays;
    }

    public String convertMapToString() {
        StringBuilder mapAsString = new StringBuilder("**");
        for (String key : plays.keySet()) {
            mapAsString.append("The play " + key + " got you: " +  plays.get(key) + "\n   ");
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("**");
        return mapAsString.toString();
    }


    public void increaseTotalScore(int score){
        totalScore += score;
    }





}






