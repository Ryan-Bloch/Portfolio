package com.example.user.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int leftNum;
    int rightNum;
    int answer;
    ArrayList<Integer> answers = new ArrayList<>();
    android.support.v7.widget.GridLayout gridLayout;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView equation;
    ConstraintLayout gameLayout;
    int totalTries;
    int correctScore;
    int time;
    TextView score;
    String message = "";
    TextView roundMessage;
    CountDownTimer countDown;
    TextView timerCount;
    Button playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        equation = (TextView) findViewById(R.id.equation);
        score = (TextView) findViewById(R.id.score);
        roundMessage = (TextView) findViewById(R.id.message);
        timerCount = (TextView) findViewById(R.id.countDown);
        playAgain = (Button) findViewById(R.id.playAgain);
        gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);


    }

    public void gameStart(View view) {
        gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);
        Button go = (Button) findViewById(R.id.goButton);
        gameLayout.setVisibility(View.VISIBLE);
        go.setVisibility(View.INVISIBLE);
        totalTries = 0;
        correctScore = 0;
        time = 30;

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View child = gridLayout.getChildAt(i);
            child.setEnabled(true);
        }

        generateRound();

        countDown = new CountDownTimer(31000, 1000) {
            @Override
            public void onTick(long l) {
                timerCount.setText(String.valueOf(time) + "s");
                time -= 1;
            }

            @Override
            public void onFinish() {
                roundMessage.setText("Done!");
                playAgain.setVisibility(View.VISIBLE);

                for (int i = 0; i < gridLayout.getChildCount(); i++) {
                    View child = gridLayout.getChildAt(i);
                    child.setEnabled(false);
                }
            }
        }.start();
    }



    public void generateRound() {

        roundMessage.setText(message);
        score.setText(correctScore + "/" + totalTries);

        Random random = new Random();
        int locationOfCorrectAnswer = random.nextInt(4);

        leftNum = random.nextInt(45);
        rightNum = random.nextInt(45);
        answer = leftNum + rightNum;
        System.out.println(leftNum);
        System.out.println(rightNum);
        System.out.println(answer);

        equation.setText(leftNum+ "+" + rightNum);

        answers.clear();

        for(int i = 0; i < 4; i++) {
             if(i == locationOfCorrectAnswer) {
                 answers.add(answer);
             }

             else {
                 int wrongAnswer = random.nextInt(45);

                 while (wrongAnswer == answer) {
                     wrongAnswer = random.nextInt(45);
                 }
                 answers.add(wrongAnswer);
             }
        }

        button0.setText(String.valueOf(answers.get(0)));
        button1.setText(String.valueOf(answers.get(1)));
        button2.setText(String.valueOf(answers.get(2)));
        button3.setText(String.valueOf(answers.get(3)));

    }

    public void chooseAnswer(View view) {
        Button answerClicked = (Button) view;

        if(answerClicked.getText().equals(String.valueOf(answer))) {
            correctScore++;
            message = "Correct! :)";
            roundMessage.setVisibility(View.VISIBLE);
        }

        else {
            message = "Incorrect :(";
            roundMessage.setVisibility(View.VISIBLE);
        }

        totalTries ++;

        generateRound();
    }
}
