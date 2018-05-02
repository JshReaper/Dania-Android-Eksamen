package com.jshch.androidgameeksamen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button playB;
    Button exitB;

    FileOutputStream fileStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        readScore();

        playB = findViewById(R.id.playB);
        playB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playButtonEvent();
            }
        });

        exitB = findViewById(R.id.ExitB);
        exitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();

        playB.setOnClickListener(null);
        exitB.setOnClickListener(null);
    }

    void playButtonEvent(){
        //Intent intent = new Intent(this,GameActivity.class);
        //startActivity(intent);
    }

    void readScore() {
        TextView tx = findViewById(R.id.scoreView);

        final String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int score = prefs.getInt("localScore", 0); //0 is the default value.
        tx.setText("high score: " + score);

        try {
            fileStream = openFileOutput("localScore.txt", Context.MODE_PRIVATE);

            InputStream is = getAssets().open("localScore.txt");
            int size = is.available();
            if (size != 0) {
                byte[] buffer = new byte[size];

                is.read(buffer);
                is.close();

                String bufferTransfer = new String(buffer);

                int currentBestScore = Integer.parseInt(bufferTransfer);

                tx.setText("Best score: " + currentBestScore);
            } else {
                tx.setText("No scores recorded");
            }


        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
    }
}