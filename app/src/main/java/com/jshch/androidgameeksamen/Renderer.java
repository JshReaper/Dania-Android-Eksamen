package com.jshch.androidgameeksamen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Renderer extends Component implements DrawAble,LoadAble {

    public Bitmap bitmap;
    int img;
    Resources resources;

    public Renderer(GameObject go, Resources resources, int img) {
        super(go);
        this.resources = resources;
        this.img = img;
    }



    public void Draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(bitmap,go.getTransform().GetPosition().getX(),go.getTransform().GetPosition().getY(),paint );

    }

    public Bitmap GetBitmap(){
        return bitmap;
    }

    @Override
    public void LoadContent() {
       bitmap = BitmapFactory.decodeResource(resources, img);
    }
}
