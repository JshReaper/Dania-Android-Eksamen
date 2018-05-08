package com.jshch.androidgameeksamen;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.LinkedList;

public class GameWorld {
    boolean Playing = true;
    LinkedList<GameObject> gameObjects;
    LinkedList<GameObject> gameObjectsToDestroy;
    CollisionChecker collisionChecker;
    Thread colThread = null;

    private static GameWorld instance;
    protected GameWorld(){

        //game objects ini
        gameObjects = new LinkedList<>();
        gameObjectsToDestroy = new LinkedList<>();
        AddGameObjects();
        LoadConent();

        //collision
        collisionChecker = new CollisionChecker();
        colThread = new Thread(collisionChecker);
        colThread.start();
    }
    void AddGameObjects(){
        //add all game objects
        Transform transTurret = new Transform(new Vector2(100,50),Vector2.Zero());
        GameObject turretObj = new GameObject(transTurret);
        Turret turret = new Turret(turretObj);
        turretObj.components.add(turret);
        turretObj.tag = "turret";

        Transform trans = new Transform(new Vector2(100,50),Vector2.Zero());
        GameObject tankObj = new GameObject(trans);
        //Renderer render = new Renderer(tankObj,);
        Tank tank = new Tank(tankObj,turretObj);
        tankObj.components.add(tank);
        tankObj.tag = "player";

    }
    void LoadConent(){
        for(GameObject go: gameObjects){
            go.LoadContent();
        }
    }
    public static GameWorld getInstance() {
        if(instance == null){
            instance = new GameWorld();
        }
        return instance;
    }

    boolean Destroy(GameObject go){
        boolean success = false;
        if(gameObjects.contains(go)){
            success = gameObjectsToDestroy.add(go);
        }

        return success;
    }

    public void Update(float deltaTime){
        for (GameObject go : gameObjects){
            go.Update(deltaTime);
        }
        if(gameObjectsToDestroy.size() > 0) {
            for (GameObject goD : gameObjectsToDestroy) {
                gameObjects.remove(goD);
            }
            gameObjectsToDestroy = new LinkedList<>();
        }
    }

    public void Draw(Canvas canvas, Paint paint){
        for(GameObject go : gameObjects){
            go.Draw(canvas,paint);
        }
    }
}
