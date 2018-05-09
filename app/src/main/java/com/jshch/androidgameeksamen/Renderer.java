package com.jshch.androidgameeksamen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Renderer extends Component implements DrawAble,LoadAble {

    public Bitmap bitmap;
    int img;

    public Renderer(GameObject go, int img) {
        super(go);
        this.img = img;
        tag = "Renderer";
        bitmap = BitmapFactory.decodeResource(GameView.resources, img);
    }



    public void Draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(bitmap,go.getTransform().GetPosition().getX(),go.getTransform().GetPosition().getY(),paint );

    }

    public Bitmap GetBitmap(){
        return bitmap;
    }

    @Override
    public void LoadContent(Resources resources) {
       bitmap = BitmapFactory.decodeResource(resources, img);

    }
}
