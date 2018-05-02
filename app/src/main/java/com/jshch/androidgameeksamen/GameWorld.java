package com.jshch.androidgameeksamen;

import java.util.LinkedList;

public class GameWorld {
    boolean Playing = true;
    LinkedList<GameObject> gameObjects;
    private static GameWorld instance;
    protected GameWorld(){
        gameObjects = new LinkedList<>();
    }

    public static GameWorld getInstance() {
        if(instance == null){
            instance = new GameWorld();
        }
        return instance;
    }
}
