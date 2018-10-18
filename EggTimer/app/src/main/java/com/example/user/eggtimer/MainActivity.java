package com.example.user.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    int minute;
    int seconds;
    double number;
    double secDecimals;
    int fullSeconds;
    TextView timer;
    CountDownTimer countDown;
    boolean timerOn = false;
    MediaPlayer alarm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        timer = (TextView) findViewById(R.id.textView);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                fullSeconds = i;
                setTimerText(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void setCountDownTimer(View view) {
        Button timerButton = (Button) findViewById(R.id.button2);
        timerButton.setText("stop");
        alarm = MediaPlayer.create(this, R.raw.rooster );

        if(!timerOn) {
            countDown = new CountDownTimer(fullSeconds*1000, 1000) {
                @Override
                public void onTick(long l) {
                    fullSeconds = fullSeconds - 1;
                    setTimerText(fullSeconds);
                }

                @Override
                public void onFinish() {
                    alarm.start();
                }
            }.start();
            timerButton.setText("stop");
            timerOn = true;
            seekBar.setEnabled(false);
        }
        else {
            countDown.cancel();
            timerButton.setText("start");
            timerOn = false;
            seekBar.setEnabled(true);
            seekBar.setProgress(30);
        }

    }

    public void setTimerText(int i) {
        minute = i/60;
        number = (double) i/60;
        secDecimals = ((10*number - 10*minute)/10)*60;
        seconds =  (int) Math.round(secDecimals);

        if(seconds<10) {
            timer.setText(minute+" : 0"+seconds);
        }
        else {
            timer.setText(minute+" : "+seconds);
        }
    }
}
