package com.jshch.androidgameeksamen;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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
    static Context context;

    // A Canvas and a Paint object
    Canvas canvas;
    Paint paint;


    public GameView(Context context) {
        super(context);
        this.context = context;
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
            deltaTime = timeThisFrame / 1000.0f;
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
            canvas.drawColor(Color.argb(255, 26, 128, 182));

            // Choose the brush color for drawing
            paint.setColor(Color.argb(255, 249, 129, 0));

            // Make the text a bit bigger
            paint.setTextSize(45);

            // Display the current fps on the screen
            canvas.drawText("FPS:" + fps, 20, 40, paint);


            //test img
            /*Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.kappa);
            Log.d("message", "draw: "+bitmap.getHeight());
            canvas.drawBitmap(bitmap,50,50,paint );*/

            //draw all GO's
            GameWorld.getInstance().Draw(canvas, paint);

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
            //Log.e("Error:", "joining thread");
        }
        GameWorld.getInstance().OnPause();
    }

    // If SimpleGameEngine Activity is started theb
    // start our thread.
    public void resume() {
        GameWorld.getInstance().Playing = true;
        GameWorld.getInstance().OnResume();
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:


                //horizontal
                motionEvent.getAxisValue(0);

                //vertical
                motionEvent.getAxisValue(1);

                GameWorld.getInstance().Controller(motionEvent.getAxisValue(0), motionEvent.getAxisValue(1),true);
                break;

            // Player has removed finger from screen
            case MotionEvent.ACTION_UP:

                // Set isMoving so Bob does not move

                GameWorld.getInstance().Controller(motionEvent.getAxisValue(0), motionEvent.getAxisValue(1),false);

                break;

        }
        return true;
    }
}