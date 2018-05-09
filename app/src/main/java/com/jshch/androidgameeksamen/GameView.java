package com.jshch.androidgameeksamen;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.LinkedList;
import java.util.List;

public class GameView extends SurfaceView implements Runnable {
    // This variable tracks the game frame rate
    long fps;

    Thread gameThread = null;
    CollisionChecker collisionChecker;
    // This is used to help calculate the fps
    private long timeThisFrame;
    float deltaTime;
    // This is new. We need a SurfaceHolder
    // When we use Paint and Canvas in a thread
    // We will see it in action in the draw method soon.
    SurfaceHolder ourHolder;

    static Resources resources;

    // A Canvas and a Paint object
    Canvas canvas;
    Paint paint;

    public GameView(Context context) {
        super(context);
        ourHolder = getHolder();
        paint = new Paint();
        resources = getResources();
        GameWorld.getInstance().LoadContent(getResources());

    }

    @Override
    public void run() {

        while (GameWorld.getInstance().Playing) {
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
        if (ourHolder.getSurface().isValid()) {
            // Lock the canvas ready to draw
            // Make the drawing surface our canvas object
            canvas = ourHolder.lockCanvas();

            // Draw the background color
            canvas.drawColor(Color.argb(255,  26, 128, 182));

            // Choose the brush color for drawing
            paint.setColor(Color.argb(255,  249, 129, 0));

            // Make the text a bit bigger
            paint.setTextSize(45);

            // Display the current fps on the screen
            canvas.drawText("FPS:" + fps, 20, 40, paint);

            //test img
            Bitmap bitmap;
            bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.kappa);
            Log.d("message", "draw: "+bitmap.getHeight());
            canvas.drawBitmap(bitmap,50,50,paint );
            //draw all GO's
            GameWorld.getInstance().Draw(canvas,paint);

            // Draw everything to the screen
            // and unlock the drawing surface
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void update() {
        GameWorld.getInstance().Update(deltaTime);
    }

    // shutdown our thread.
    public void pause() {
        GameWorld.getInstance().Playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }

    // If SimpleGameEngine Activity is started theb
    // start our thread.
    public void resume() {
        GameWorld.getInstance().Playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

}