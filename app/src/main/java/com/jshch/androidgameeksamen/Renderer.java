package com.jshch.androidgameeksamen;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Paint;

public class Renderer extends Component implements DrawAble {

    Bitmap texture;
    SurfaceHolder ourHolder;
    SurfaceView canvas;
    Paint paint;

    public Renderer(GameObject go, SurfaceHolder holder) {
        super(go);

        if(holder != null){
            ourHolder = holder;
        }
        paint = new Paint();
    }

    public Renderer(GameObject go,SurfaceHolder holder, Bitmap texture) {
        super(go);

        this.texture = texture;
        if(holder != null)
            ourHolder = holder;
        paint = new Paint();
    }

    public void Draw(){
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

            //draw all GO's
            if(gameObjects.size() > 0) {
                for (GameObject go : gameObjects) {
                    go.Draw(canvas, paint);
                }
            }

            // Draw everything to the screen
            // and unlock the drawing surface
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    public Bitmap GetBitmap(){

    }
}
