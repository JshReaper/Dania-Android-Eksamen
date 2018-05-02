package com.jshch.androidgameeksamen;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.LinkedList;

public class GameWorld {
    boolean Playing = true;
    LinkedList<GameObject> gameObjects;
    CollisionChecker collisionChecker;
    Thread colThread;
    private static GameWorld instance;
    protected GameWorld(){
        gameObjects = new LinkedList<>();
        collisionChecker = new CollisionChecker();
        colThread = new Thread(collisionChecker);
        colThread.start();
    }

    public static GameWorld getInstance() {
        if(instance == null){
            instance = new GameWorld();
        }
        return instance;
    }


    public void Update(float deltaTime){
        for (GameObject go : gameObjects){
            go.Update();
        }
    }

    public void Draw(Canvas canvas, Paint paint){
        for(GameObject go : gameObjects){
            go.Draw(canvas, paint);
        }
    }
}
