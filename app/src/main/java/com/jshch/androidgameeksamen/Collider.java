package com.jshch.androidgameeksamen;

import android.graphics.Bitmap;

import java.util.concurrent.atomic.AtomicReference;

public class Collider extends Component {
    private Bitmap bitmap;

    public Bitmap GetBitMap(){
        return bitmap;
    }
    private Transform transform;
    Transform GetTransform(){
        return transform;
    }
    Collider(Bitmap bitmap, Transform transform){
        this.bitmap = bitmap;
        this.transform = transform;
    }
    boolean InterSectsWidthRect(Collider other){
        float myCenterX = transform.GetPosition().getX() + bitmap.getWidth() /2;
        float OtherCenterX = other.GetTransform().GetPosition().getX() + other.GetBitMap().getWidth()/2;
        float myCenterY = transform.GetPosition().getY() + bitmap.getHeight() /2;
        float OtherCenterY = other.GetTransform().GetPosition().getY() + other.GetBitMap().getHeight()/2;
        if (Math.abs(myCenterX-OtherCenterX)<=(bitmap.getWidth()+other.GetBitMap().getWidth())/2f
                && Math.abs(myCenterY-OtherCenterY)<=(bitmap.getHeight()+other.GetBitMap().getHeight())/2f)
            return true;
        else
            return false;
    }
}
