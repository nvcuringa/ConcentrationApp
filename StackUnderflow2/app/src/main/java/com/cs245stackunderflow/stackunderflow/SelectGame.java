/***************************************************************
 * file: SelectGame.java
 * author: Nick Curinga, Devin Wells, Caesar Pedroza, Tuan Pham
 * class: CS 245
 – Programming Graphical User Interfaces
 *
 * assignment: Android App
 * date last modified: 11/29/2016
 *
 * purpose: This class selects how many cards to play with.
 ****************************************************************/

package com.cs245stackunderflow.stackunderflow;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SelectGame extends AppCompatActivity {

    private EditText numberOfCards;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Concentration");
        setSupportActionBar(toolbar);



        numberOfCards = (EditText) findViewById(R.id.enteramount);
        continueButton = (Button) findViewById(R.id.continuebutton);

        numberOfCards.addTextChangedListener(new TextValidator(numberOfCards) {
            @Override
            public void validate(TextView textView, String text) {
                if (Integer.parseInt(numberOfCards.getText().toString()) % 2 != 0) {
                    numberOfCards.setError("Enter an even number");
                }
                if (Integer.parseInt(numberOfCards.getText().toString()) < 4 || Integer.parseInt(numberOfCards.getText().toString()) > 20) {
                    numberOfCards.setError("Enter an even number between 4 and 20");
                }
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
            if (Integer.parseInt(numberOfCards.getText().toString()) % 2 == 0
                    && Integer.parseInt(numberOfCards.getText().toString()) >= 4
                    && Integer.parseInt(numberOfCards.getText().toString()) <= 20) {
                Intent gi = new Intent(SelectGame.this, GameInstance.class);
                gi.putExtra("cardAmount", Integer.parseInt(numberOfCards.getText().toString()));
                startActivity(gi);
            }
            }
        });
    }
}
