package com.jshch.androidgameeksamen;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class Turret extends Component  {

    Renderer render;
    float lastAngle = 0;

    public Turret(GameObject go){
        super(go);
        render = (Renderer)GetGameObject().GetComponent("Renderer");
    }

    public void RotateTurretTo(float angle, Vector2 pivot){

        Bitmap myImg = render.GetBitmap();
        Matrix rotation = new Matrix();
        rotation.postRotate(angle - lastAngle, pivot.getX(), pivot.getY());
        myImg = Bitmap.createBitmap(myImg,0,0,myImg.getWidth(), myImg.getHeight(),rotation, true);
        //set lastAngle to the new angle so that this works next time
        lastAngle = angle;
    }




}