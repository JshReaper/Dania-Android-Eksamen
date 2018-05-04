package com.jshch.androidgameeksamen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class TerrainBox extends Component implements CollideAble, DrawAble {

    Bitmap box_img;

    public TerrainBox(GameObject go, Bitmap box_img) {
        super(go);

        this.box_img = box_img;
    }

    @Override
    public void Draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(box_img,go.getTransform().GetPosition().getX(),go.getTransform().GetPosition().getY(),paint );

    }


    public void Update(float deltaTime) {

    }

    @Override
    public void OnCollisionEnter(Collider other) {

    }

    @Override
    public void OnCollisionStay(Collider other) {

    }

    @Override
    public void OnCollisionExit(Collider other) {

    }
}
