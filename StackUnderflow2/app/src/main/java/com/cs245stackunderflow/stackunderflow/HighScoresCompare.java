package com.cs245stackunderflow.stackunderflow;

import java.util.Comparator;



public class HighScoresCompare implements Comparator<HighScores>
{
    public int compare(HighScores h1, HighScores h2)
    {
        return h1.getScore().compareTo(h2.getScore());
    }
}


