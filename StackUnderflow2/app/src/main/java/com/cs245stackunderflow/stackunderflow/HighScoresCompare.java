/***************************************************************
 * file: HighScoresCompare.java
 * author: Nick Curinga, Devin Wells, Caesar Pedroza, Tuan
 * class: CS 245
 – Programming Graphical User Interfaces
 *
 * assignment: Android App
 * date last modified: 11/29/2016
 *
 * purpose: This class compares two high scores.
 ****************************************************************/

package com.cs245stackunderflow.stackunderflow;

import java.util.Comparator;

public class HighScoresCompare implements Comparator<HighScores>
{
    public int compare(HighScores h1, HighScores h2)
    {
        return h1.getScore().compareTo(h2.getScore());
    }
}


