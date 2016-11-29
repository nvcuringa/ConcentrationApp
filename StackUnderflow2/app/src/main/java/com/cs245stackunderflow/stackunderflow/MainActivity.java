package com.cs245stackunderflow.stackunderflow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mInitialPlay;
    private Button mHighScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInitialPlay = (Button)findViewById(R.id.initplay);
        mInitialPlay.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                Intent sg = new Intent(MainActivity.this,SelectGame.class);
                startActivity(sg);


            }
        });
    }








}
