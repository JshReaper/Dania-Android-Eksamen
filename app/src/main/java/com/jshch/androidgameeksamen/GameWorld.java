package com.jshch.androidgameeksamen;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
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

        //collision
        collisionChecker = new CollisionChecker();
        colThread = new Thread(collisionChecker);
        colThread.start();
    }
    void AddGameObjects(){
        //add all game objects
        int standardYpos = 700;
        int blockCount = 240;
        int xPos = 0;
        int offset = 800;
        for (int i = 0;i < blockCount;i++){
            Transform trans = new Transform(new Vector2(xPos,standardYpos - MapInfoGenerator.HeightFromXcoord(xPos,offset)),new Vector2(1,2.6f));
            xPos += 10;

            GameObject go = new GameObject(trans);
            Renderer rd = new Renderer(go, R.drawable.sandblock);
            Collider col = new Collider(go);
            TerrainBox tb = new TerrainBox(go);
            go.components.add(rd);
            go.components.add(col);
            go.components.add(tb);
            go.tag = "ground";
            AddGameObject(go);

        }

        Transform transTurret = new Transform(new Vector2(100,50),new Vector2(0.15f,0.15f));
        GameObject turretObj = new GameObject(transTurret);
        Turret turret = new Turret(turretObj);
        turretObj.components.add(new Renderer(turretObj, R.drawable.tankturretfat));
        turretObj.components.add(turret);
        turretObj.tag = "turret";


        Transform trans = new Transform(new Vector2(150,500),new Vector2(0.15f,0.15f));
        GameObject tankObj = new GameObject(trans);
        Tank tank = new Tank(tankObj,turretObj);
        AudioController ac = new AudioController(tankObj, GameView.context);
        ac.SetSound(R.raw.tankgunsound);
        tankObj.components.add(new Renderer(tankObj,R.drawable.tank));
        tankObj.components.add(tank);
        tankObj.components.add(ac);
        tankObj.tag = "player";

        gameObjects.add(turretObj);
        gameObjects.add(tankObj);


        //buttons
        GameObject btnright = new GameObject(new Transform(new Vector2(300,100),1));
        btnright.components.add(new Renderer(btnright,R.drawable.rightarrow));
        btnright.components.add(new  GameButton(btnright,"right"));
        gameObjects.add(btnright);

        GameObject btnleft = new GameObject(new Transform(new Vector2(100,100),1));
        btnleft.components.add(new Renderer(btnleft,R.drawable.leftarrow));
        btnleft.components.add(new  GameButton(btnleft,"left"));
        gameObjects.add(btnleft);


    }

    public void AddGameObject(GameObject go){
        gameObjects.add(go);
        go.LoadContent(GameView.resources);
    }

    void LoadContent(Resources resources){


        for(GameObject go: gameObjects){
            go.LoadContent(resources);
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

    public void Controller(float axisX, float axisY) {
        for(GameObject go : gameObjects){
            go.Controller(axisX,axisY);
        }
    }
}
