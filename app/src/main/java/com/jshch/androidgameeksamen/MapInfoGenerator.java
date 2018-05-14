package com.jshch.androidgameeksamen;

import android.util.Log;

public class MapInfoGenerator {



public static float HeightFromXcoord(float x, float Offset){
    float height = 0;
    Log.d("message","Current X: " + x);
    float A = ((float)(319.5 * Math.sin(Math.toRadians((x+Offset))*0.2)));
    float B = ((float)(143.5 * Math.sin(Math.toRadians((x+Offset))*0.4)));
    float C = ((float)(71.4 * Math.sin(Math.toRadians((x+Offset))*1.2)));
    float D = ((float)(73.1 * Math.sin(Math.toRadians((x+Offset))*0.9)));

height = A - B + C + D;


    return height / 2;
}
}
