package com.jshch.androidgameeksamen;

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
}
