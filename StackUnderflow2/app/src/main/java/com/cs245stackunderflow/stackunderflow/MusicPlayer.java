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