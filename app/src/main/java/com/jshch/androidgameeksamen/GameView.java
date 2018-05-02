package com.jshch.androidgameeksamen;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceView;

import java.util.LinkedList;
import java.util.List;

public class GameView extends SurfaceView implements Runnable {
    boolean playing;
    // This variable tracks the game frame rate
    long fps;

    Thread colT;
    CollisionChecker collisionChecker;
    // This is used to help calculate the fps
    private long timeThisFrame;
    public GameView(Context context) {
        super(context);

    }

    @Override
    public void run() {

        while (playing) {

            // Capture the current time in milliseconds in startFrameTime
            long startFrameTime = System.currentTimeMillis();

            // Update the frame
            update();

            // Draw the frame
            draw();

            // Calculate the fps this frame
            // We can then use the result to
            // time animations and more.
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame > 0) {
                fps = 1000 / timeThisFrame;
            }

        }
    }

    private void draw() {

    }

    private void update() {

    }
    // shutdown our thread.
    public void pause() {
        playing = false;
        collisionChecker.playing = false;
        try {
            colT.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }

    // If SimpleGameEngine Activity is started theb
    // start our thread.
    public void resume() {
        playing = true;
        collisionChecker.playing = true;
        colT = new Thread(collisionChecker);
        colT.start();
    }

}