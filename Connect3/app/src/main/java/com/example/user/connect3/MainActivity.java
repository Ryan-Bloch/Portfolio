package com.example.user.connect3;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // yellow: 0, red: 1, 2: empty

    int gameState[] = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,4,8}, {2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    int player = 0;
    boolean gameActive = true;
    int roundCount = 0;
    boolean win = false;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int position = Integer.parseInt(counter.getTag().toString());
        Button button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.textView);


        if(gameState[position] == 2 && gameActive == true) {

            gameState[position] = player;

            if (player == 0) {
                counter.setImageResource(R.drawable.yellow);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                player = 0;
            }

            counter.setTranslationY(-1500);
            counter.animate().translationYBy(1500).setDuration(300);
            String message = "";

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    if (player == 1) {
                        message = "yellow";
                    } else {
                        message = "red";
                    }
                    gameActive = false;
                    win = true;

                    textView.setText(message + " won!");
                    button.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    break;
                }
            }

            if(roundCount==8 && win==false) {
                textView.setText("Noone won!");
                button.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
            }
            roundCount++;
        }
    }

    public void playAgain(View view) {
        Button button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.textView);
        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        android.support.v7.widget.GridLayout myGridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<myGridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) myGridLayout.getChildAt(i);
            counter.setImageDrawable(null);
            gameState[i] = 2;
        }

        player = 0;
        gameActive = true;
        win = false;
        roundCount = 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
