package com.jshch.androidgameeksamen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class TerrainBox extends Component implements CollideAble,UpdateAble {

    public TerrainBox(GameObject go) {
        super(go);
        tag = "TerrainBox";
    }



    public void Update(float deltaTime) {

        //check if player above me
        //if so are they on the ground above me?
        //if so then dont apply gravity anymore

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
