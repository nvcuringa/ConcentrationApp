package com.cs245stackunderflow.stackunderflow;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class GameInstance extends AppCompatActivity {


    protected GridLayout gl;
    private ArrayList<Card> theCards;
    private Stack<Integer> imageID;
    private int amount;
    private Card cardSelect1;
    private Card cardSelect2;
    private int count;
    private GameEngine ge;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_instance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Intent sg = getIntent();
        amount = sg.getIntExtra("cardAmount", 0);


        gl = (GridLayout) findViewById(R.id.thegrid);


        if (isCardAmountCorrect(amount)) {
            theCards = new ArrayList<Card>();
            imageID = new Stack<Integer>();
            initCards();
            addCardsToGrid();

        }

        ge = new GameEngine(amount);

        for(int a = 0; a < amount; a++) {
            theCards.get(a).setOnClickListener(new View.OnClickListener() {

                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                public void onClick(View v) {


                    if(count == 0) {
                        cardSelect1 = (Card) v;
                        cardSelect1.flip();
                        count++;
                    }
                    else if(count == 1)
                    {
                        cardSelect2 = (Card) v;
                        cardSelect2.flip();
                        count++;
                    }
                    else
                    {
                            if(ge.isMatch(cardSelect1,cardSelect2))
                            {
                                findCard(cardSelect1).setEnabled(false);
                                findCard(cardSelect2).setEnabled(false);

                            }
                            else
                            {
                                findCard(cardSelect1).flip();
                                findCard(cardSelect2).flip();
                            }
                        }
                        count = 0;


                }
            });








        }




}

    protected Card findCard(Card c)
    {
       for(int i = 0; i < amount; i++)
       {
           if(theCards.get(i).getCardID() == c.getCardID())
           {
               return theCards.get(i);
           }
       }
        return null;

    }



    protected void addCardsToGrid() {
        if (amount == 20 || amount == 18) {
            gl.setRowCount(5);
            gl.setColumnCount(4);
        } else if (amount == 16 || amount == 14) {
            gl.setRowCount(4);
            gl.setColumnCount(4);
        } else if (amount == 12 || amount == 10) {
            gl.setRowCount(4);
            gl.setColumnCount(3);
        } else if (amount == 8 || amount == 6) {
            gl.setRowCount(3);
            gl.setColumnCount(3);
        } else if (amount == 4) {
            gl.setRowCount(2);
            gl.setColumnCount(2);
        }


        for (int j = 0; j < amount; j++)
            gl.addView(theCards.get(j));


    }


    protected void initCards() {

        imageID.push(R.drawable.cppcard);
        imageID.push(R.drawable.csharpcard);
        imageID.push(R.drawable.gocard);
        imageID.push(R.drawable.pythoncard);
        imageID.push(R.drawable.javacard);
        imageID.push(R.drawable.jscard);
        imageID.push(R.drawable.perlcard);
        imageID.push(R.drawable.phpcard);
        imageID.push(R.drawable.sqlcard);
        imageID.push(R.drawable.rubycard);

        Collections.shuffle(imageID);

        int pairs = amount / 2;
        int thePair = 0;

        for (int i = 0; i < pairs; i++) {
            thePair = imageID.pop();



            theCards.add(new Card(this,thePair));
            theCards.add(new Card(this,thePair));


        }

        Collections.shuffle(theCards);


    }

    protected boolean isCardAmountCorrect(int am) {

        if (am % 2 == 0 && am >= 4 && am <= 20)
            return true;
        else
            return false;

    }

}
