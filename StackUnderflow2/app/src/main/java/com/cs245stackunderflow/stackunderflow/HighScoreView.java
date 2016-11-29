package com.cs245stackunderflow.stackunderflow;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class HighScoreView extends AppCompatActivity {


    private TextView highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_high_score_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);




        Intent hs = getIntent();
        String str = hs.getStringExtra("theHighScore");

       highScore = (TextView) findViewById(R.id.viewhs);


       highScore.setText(str);
       highScore.setTextSize(20);

       toolbar.setTitle("HighScores");

        setSupportActionBar(toolbar);

    }

}
