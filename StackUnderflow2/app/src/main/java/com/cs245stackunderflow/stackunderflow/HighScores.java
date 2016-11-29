/***************************************************************
 * file: HighScores.java
 * author: Nick Curinga, Devin Wells, Caesar Pedroza, Tuan
 * class: CS 245
 – Programming Graphical User Interfaces
 *
 * assignment: Android App
 * date last modified: 11/29/2016
 *
 * purpose: This class saves high scores.
 ****************************************************************/
package com.cs245stackunderflow.stackunderflow;

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
