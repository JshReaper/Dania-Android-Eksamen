package com.jshch.androidgameeksamen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;

public class Turret extends Component implements LoadAble  {

    Renderer render;
    float lastAngle = 0;
    Bitmap myOriginalPic = null;
    public Vector2 offset;
    public float width;
    public float originalWidth;
    public float yOffset;

    public Turret(GameObject go){
        super(go);
        tag = "Turret";
    }

    @Override
    public void LoadContent(Resources R){
        render = (Renderer)GetGameObject().GetComponent("Renderer");
        offset = new Vector2(-render.bitmap.getWidth(),0);

    }

    public void RotateTurretTo(float angle, Vector2 pivot){
        if(myOriginalPic == null){
            myOriginalPic = render.bitmap;
            originalWidth = myOriginalPic.getWidth();
        }



        yOffset = (float) ((Math.sin(Math.toRadians(angle))) * originalWidth);
        //used for debugging:
        //Log.d("message","Current offset " + yOffset);
        Bitmap myImg = myOriginalPic;
        Matrix rotation = new Matrix();
        rotation.postRotate((-angle), 0, myImg.getHeight()/2);
        myImg = Bitmap.createBitmap(myImg,0,0,myImg.getWidth(), myImg.getHeight(),rotation, true);
        render.bitmap = myImg;
        width = myImg.getWidth();

    }




}
