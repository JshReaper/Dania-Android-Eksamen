package com.jshch.androidgameeksamen;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Terrain extends Component implements CollideAble, DrawAble, UpdateAble {


    public Terrain(GameObject go) {
        super(go);
    }

    @Override
    public void Update(float deltaTime) {

    }

    @Override
    public void Draw(Canvas canvas, Paint paint){

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
