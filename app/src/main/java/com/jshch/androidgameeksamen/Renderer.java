package com.jshch.androidgameeksamen;

import android.graphics.Bitmap;

public class Renderer extends Component {

    Bitmap bitmap;
    public Bitmap GetBitMap(){
        return bitmap;
    }
    public Renderer(GameObject go){
        super(go);

    }
}
