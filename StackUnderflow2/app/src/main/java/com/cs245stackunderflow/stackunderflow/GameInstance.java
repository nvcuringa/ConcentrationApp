package com.cs245stackunderflow.stackunderflow;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Handler;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class GameInstance extends AppCompatActivity implements View.OnClickListener{

    protected GridLayout gl;
    private ArrayList<Card> theCards;
    private Stack<Integer> imageID;
    private int amount;
    private Card cardSelect1;
    private Card cardSelect2;

    private GameEngine ge;
    private int count;

    private Button tryAgain;
    private Button endGame;
    private Button newGame;


    private String[] names = new String[3];
    private String[] scores = new String[3];
    ArrayList<String> list = new ArrayList<String>(3);
    private static Context context;

    private TextView highScore;

    private Intent hs;



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

       count = 0;

        Intent sg = getIntent();
        amount = sg.getIntExtra("cardAmount", 0);


        gl = (GridLayout) findViewById(R.id.thegrid);

        tryAgain = (Button) findViewById(R.id.tryagain);
        tryAgain.setOnClickListener(GameInstance.this);


        endGame = (Button) findViewById(R.id.endgame);
        endGame.setOnClickListener(GameInstance.this);

        newGame = (Button) findViewById(R.id.newgame);
        newGame.setOnClickListener(GameInstance.this);


        if (isCardAmountCorrect(amount)) {
            theCards = new ArrayList<Card>();
            imageID = new Stack<Integer>();
            initCards();
            addCardsToGrid();
        }

        ge = new GameEngine(amount);

        for(int a = 0; a < amount; a++) {
                 theCards.get(a).setOnClickListener(GameInstance.this);

        }





                            }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {


             if(v.getId()== R.id.tryagain && !(ge.isOver()))
             {
                 if(cardSelect1 != null) {
                     cardSelect1.flip();
                     cardSelect1 = null;
                 }

                 if(cardSelect2 != null) {
                     cardSelect2.flip();
                     cardSelect2 = null;
                 }
                 return;
             }

            if(v.getId()== R.id.endgame && !(ge.isOver())) {

                for(int i = 0; i < amount; i++) {
                    theCards.get(i).show();
                    theCards.get(i).setEnabled(false);

                }
                return;
            }

            if(v.getId()== R.id.newgame)
            {
               Intent i = getIntent();
                finish();
                startActivity(i);
                return;
            }



                Card c = (Card) v;



             if(cardSelect1 == null)
             {
                 cardSelect1 = c;
                 cardSelect1.flip();
                 return;
             }

             if(cardSelect2 == null)
             {
                 cardSelect2 = c;
                 cardSelect2.flip();



             }

             if(ge.isMatch(cardSelect1,cardSelect2))
             {

                 cardSelect1.setEnabled(false);
                 cardSelect2.setEnabled(false);

                 cardSelect1 = null;
                 cardSelect2 = null;
             }
        updateTextView();

        if(ge.isOver())
        {
            //Call ge.getAmountOfCards() to get card game type
            //Call ge.getScore() to get score
            //HighScore code
            //Create a new class highscore and instantiate here.
            try {
                checkNewHighScore();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



        }


    }

    public void checkNewHighScore() throws FileNotFoundException {
        try {
            int gametype = ge.getAmountOfCards();
            String file = "highscores" + gametype + ".txt";
            Scanner inputFile = new Scanner(getAssets().open(file));
            String temp = "";
            int index = 0;
            while (inputFile.hasNextLine()) {
                temp = (inputFile.nextLine());
                int i = 0;
                //System.out.println(temp);
                for (char c : temp.toCharArray()) {
                    if (c == '.') {
                        if (index > 4) {
                            index = 4;
                        }
                        names[index] = temp.substring(0, i);
                        scores[index] = temp.substring(i + 4, temp.length());
                        list.add(names[index] + "...." + scores[index]);
                        //System.out.println(names[index] + "...." + scores[index]);
                        index++;
                        break;
                    }
                    i++;
                }
            }
        } catch (IOException e) {}

        sortArrays();
        int smallest = Integer.parseInt(scores[2]);
        for (String i : scores) {
            if (Integer.parseInt(i) <= smallest) {
                smallest = Integer.parseInt(i);
            }
        }
        if (ge.getScore() > smallest) {
            //String name = JOptionPane.showInputDialog(null, "Enter The Name You Want To Display In HighScores", "NEW HIGH SCORE", JOptionPane.QUESTION_MESSAGE);

            //final String[] name = new String[1];
            final EditText txtUrl = new EditText(this);
            new AlertDialog.Builder(this)
                    .setTitle("New Highscore!!")
                    .setMessage("Enter your username")
                    .setView(txtUrl)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String name = txtUrl.getText().toString();
                            try {
                                saveFile(name);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    })
                    .show();
        }
    }
    public void saveFile(String input) throws FileNotFoundException {
        GameInstance.context = getApplicationContext();
        //Context context = GameInstance.instance.getApplicationContext();
        int gametype = ge.getAmountOfCards();
        String file = "highscores" + gametype + ".txt";

        scores[2] = ge.getScore() + "";
        names[2] = input;
        sortArrays();
        list.clear();
        for (int i = 0; i < scores.length; i++) {
            list.add(names[i] + "...." + scores[i]);
        }

        try {
            FileOutputStream outputStream = openFileOutput(file, Context.MODE_PRIVATE);
                        for (String j : list) {
            outputStream.write(j.getBytes());
            }

            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
///////////// FOR HIGHSCORE BUTTON ///////////
        try {
            FileInputStream inputStream = openFileInput(file);
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);

            }
            r.close();
            inputStream.close();

            // display to screen
            //System.out.println(total);



            hs = new Intent(GameInstance.this, HighScoreView.class);
            hs.putExtra("theHighScore", total.toString());

            Log.d("File", "File contents: " + total);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //////////////////////

        startActivity(hs);

    }


    public void sortArrays() {
        int largest = Integer.parseInt(scores[0]), index = 0, tempNum = 0;
        String temp = "";

        for (int i = 0; i < names.length; i++) {
            index = i;
            largest = Integer.parseInt(scores[i]);
            for (int j = i; j < names.length; j++) {
                if (Integer.parseInt(scores[j]) > largest) {
                    largest = Integer.parseInt(scores[j]);
                    index = j;
                    //System.out.println("Largest : " + largest + "index = " + index);
                }
            }
            //System.out.println("swapping");
            temp = names[i];
            names[i] = names[index];
            names[index] = temp;
            tempNum = Integer.parseInt(scores[i]);
            scores[i] = scores[index];
            scores[index] = tempNum + "";
        }
    }
    //////////////////






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
        int orientation = gl.getResources().getConfiguration().orientation;
        if (orientation == ORIENTATION_PORTRAIT) {
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
        }
        if (orientation == ORIENTATION_LANDSCAPE) {
            if (amount == 20) {
                gl.setRowCount(3);
                gl.setColumnCount(7);
            } else if (amount == 18) {
                gl.setRowCount(3);
                gl.setColumnCount(6);
            } else if (amount == 16) {
                gl.setRowCount(3);
                gl.setColumnCount(6);
            } else if (amount == 14) {
                gl.setRowCount(3);
                gl.setColumnCount(5);
            } else if (amount == 12 || amount == 10) {
                gl.setRowCount(3);
                gl.setColumnCount(4);
            } else if (amount == 8) {
                gl.setRowCount(3);
                gl.setColumnCount(3);
            } else if (amount == 6) {
                gl.setRowCount(3);
                gl.setColumnCount(2);
            } else if (amount == 4) {
                gl.setRowCount(2);
                gl.setColumnCount(2);
            }
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

    public void updateTextView() {
        TextView textView = (TextView) findViewById(R.id.scoreBox);
        textView.setText(ge.getScore()+"");
    }

}
