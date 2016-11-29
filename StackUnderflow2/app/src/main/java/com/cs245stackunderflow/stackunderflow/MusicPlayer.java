/***************************************************************
 * file: MusicPlayer.java
 * author: Nick Curinga, Devin Wells, Caesar Pedroza, Tuan
 * class: CS 245
 – Programming Graphical User Interfaces
 *
 * assignment: Android App
 * date last modified: 11/29/2016
 *
 * purpose: This class controls the music.
 ****************************************************************/
package com.cs245stackunderflow.stackunderflow;

import android.media.MediaPlayer;
public class MusicPlayer {
    //makes 1 musicplayer that will be used. no other instance will be created from orientation change
    MediaPlayer player;
    private static volatile MusicPlayer instance = null;
    private MusicPlayer() { }

    public static MusicPlayer getInstance() {
        if (instance == null){

            synchronized (MusicPlayer.class){
                if (instance == null){
                    instance = new MusicPlayer();
                }
            }
        }
        return instance;
    }
}