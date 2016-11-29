package com.cs245stackunderflow.stackunderflow;

/**
 * Created by Nick on 11/28/2016.
 */

public class HighScores {

    private String hScore;
    private String hName;


    public HighScores(String hs,String name)
    {

        hScore = hs;
        hName = name;
    }

    public String getScore()
    {
        return hScore;
    }

    public String getName() { return hName;}



}
