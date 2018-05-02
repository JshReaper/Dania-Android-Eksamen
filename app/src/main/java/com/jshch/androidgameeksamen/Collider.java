package com.jshch.androidgameeksamen;

import android.graphics.Bitmap;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

public class Collider extends Component {

    public LinkedList<Collider> OtherColliders;
    Collider(GameObject go){
        super(go);
        OtherColliders = new LinkedList<>();
    }
    boolean InterSectsWidthRect(Collider other){
        Renderer renderer =(Renderer) go.GetComponent("Renderer");
        Bitmap bitmap =renderer.GetBitmap();
        Renderer otherRenderer =(Renderer) other.go.GetComponent("Renderer");
        Bitmap otherBitMap =otherRenderer.GetBitmap();
        float myCenterX = go.getTransform().GetPosition().getX() + bitmap.getWidth() /2;
        float OtherCenterX = go.getTransform().GetPosition().getX() + otherBitMap.getWidth()/2;
        float myCenterY = go.getTransform().GetPosition().getY() + bitmap.getHeight() /2;
        float OtherCenterY = go.getTransform().GetPosition().getY() + otherBitMap.getHeight()/2;
        if (Math.abs(myCenterX-OtherCenterX)<=(bitmap.getWidth()+otherBitMap.getWidth())/2f
                && Math.abs(myCenterY-OtherCenterY)<=(bitmap.getHeight()+otherBitMap.getHeight())/2f)
            return true;
        else
            return false;
    }
}
