package com.jshch.androidgameeksamen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.sql.Time;
import java.util.ArrayList;


public class Terrain extends Component implements DrawAble, UpdateAble{

    int NrBoxes = 20;
    ArrayList<TerrainBox> boxes;
    Bitmap BG_img, box_img;

    public Terrain(GameObject go, Bitmap BG_img, Bitmap box_img) { super(go);
        this.BG_img = BG_img;
        this.box_img = box_img;

        boxes = new ArrayList<>();
        for (int i = 0; i < NrBoxes; i++){
            boxes.add(new TerrainBox(new GameObject(new Transform(new Vector2(i * 10, 1000), 1f)), box_img));
            //boxes.get(i);
        }
    }

    @Override
    public void Draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(BG_img,go.getTransform().GetPosition().getX(),go.getTransform().GetPosition().getY(),paint );

        for (int i = 0; i < NrBoxes; i++){
            boxes.get(i).Draw(canvas, paint);
        }
    }

    @Override
    public void Update(float deltaTime) {
        for (int i = 0; i < NrBoxes; i++){
            boxes.get(i).Update(deltaTime);
        }
    }
}
