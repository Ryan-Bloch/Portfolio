package com.example.user.timestables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> timesTable = new ArrayList<>(asList(2,4,6,8,10,12,14,16,18,20));
    SeekBar seekBar;
    ListView timesTableList;
    int listNumber = 10;

    public void generateTimesT(int timesNumber) {
       timesNumber +=2;
       for(int i = 0; i<listNumber; i++){
           timesTable.set(i,(i+1)*timesNumber);
           System.out.println(timesTable.get(i));
       }

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, timesTable);
        timesTableList.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(18);
        timesTableList = (ListView) findViewById(R.id.timesTableList);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                generateTimesT(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
