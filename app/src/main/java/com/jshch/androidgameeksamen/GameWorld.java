package com.jshch.androidgameeksamen;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.LinkedList;

public class GameWorld {
    boolean Playing = true;
    LinkedList<GameObject> gameObjects;
    LinkedList<GameObject> gameObjectsToDestroy;
    LinkedList<GameObject> gameObjectsToAdd;
    CollisionChecker collisionChecker;
    Thread colThread = null;

    private static GameWorld instance;

    protected GameWorld() {

        //game objects ini
        gameObjects = new LinkedList<>();
        gameObjectsToDestroy = new LinkedList<>();
        gameObjectsToAdd = new LinkedList<>();
        AddGameObjects();

        //collision
        collisionChecker = new CollisionChecker();
        colThread = new Thread(collisionChecker);
        colThread.start();
    }

    void AddGameObjects() {
        //add all game objects
        //Terrain first:
        int standardYpos = 700;
        int blockCount = 240;
        int xPos = 0;
        int offset = 800;
        for (int i = 0; i < blockCount; i++) {
            Transform trans = new Transform(new Vector2(xPos, standardYpos - MapInfoGenerator.HeightFromXcoord(xPos, offset)), new Vector2(1, 2.6f));
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
        //Player1 tank:
        Transform transTurret = new Transform(new Vector2(100, 50), new Vector2(0.15f, 0.15f));
        GameObject turretObj = new GameObject(transTurret);
        Turret turret = new Turret(turretObj);
        turretObj.components.add(new Renderer(turretObj, R.drawable.tankturretfat));
        turretObj.components.add(turret);
        turretObj.tag = "turret";
        //Log.d("message", "Turret added");

        Transform trans = new Transform(new Vector2(150, 500), new Vector2(0.15f, 0.15f));
        GameObject tankObj = new GameObject(trans);
        Tank tank = new Tank(tankObj, turretObj, "Player1");
        AudioController ac = new AudioController(tankObj, GameView.context);
        Collider col = new Collider(tankObj);
        ac.SetSound(R.raw.tankgunsound);
        tankObj.components.add(new Renderer(tankObj, R.drawable.tank));
        tankObj.components.add(col);
        tankObj.components.add(tank);
        tankObj.components.add(ac);
        tankObj.tag = "Tank";
        AddGameObject(turretObj);
        AddGameObject(tankObj);
        //Log.d("message", "Tank added");

        //player2 tank
        Transform transTurret1 = new Transform(new Vector2(1000, 50), new Vector2(0.15f, 0.15f));
        GameObject turretObj1 = new GameObject(transTurret1);
        Turret turret1 = new Turret(turretObj1);
        turretObj1.components.add(new Renderer(turretObj1, R.drawable.tankturretfat));
        turretObj1.components.add(turret1);
        turretObj1.tag = "turret";
        //Log.d("message", "Turret added");

        Transform trans1 = new Transform(new Vector2(1000, 500), new Vector2(0.15f, 0.15f));
        GameObject tankObj1 = new GameObject(trans1);
        Tank tank1 = new Tank(tankObj1, turretObj1, "Player2");
        AudioController ac1 = new AudioController(tankObj1, GameView.context);
        Collider col1 = new Collider(tankObj1);
        ac1.SetSound(R.raw.tankgunsound);
        tankObj.components.add(new Renderer(tankObj1, R.drawable.tank));
        tankObj.components.add(col1);
        tankObj.components.add(tank1);
        tankObj.components.add(ac1);
        tankObj.tag = "Tank";
        AddGameObject(turretObj);
        AddGameObject(tankObj);

        //buttons
        GameObject btnright = new GameObject(new Transform(new Vector2(300,100),1));
        btnright.components.add(new Renderer(btnright,R.drawable.rightarrow));
        btnright.components.add(new  GameButton(btnright,"right"));
        gameObjects.add(btnright);

        GameObject btnleft = new GameObject(new Transform(new Vector2(100,100),1));
        btnleft.components.add(new Renderer(btnleft,R.drawable.leftarrow));
        btnleft.components.add(new  GameButton(btnleft,"left"));
        gameObjects.add(btnleft);

        //collider tests
       /* GameObject btnleft = new GameObject(new Transform(new Vector2(100, 100), 1));
        btnleft.components.add(new Renderer(btnleft, R.drawable.leftarrow));
        btnleft.components.add(new GameButton(btnleft, "left"));
        btnleft.components.add(new Collider(btnleft));
        gameObjects.add(btnleft);


        GameObject btnleft2 = new GameObject(new Transform(new Vector2(100, 100), 1));
        btnleft2.components.add(new Renderer(btnleft2, R.drawable.leftarrow));
        btnleft2.components.add(new GameButton(btnleft2, "left"));
        btnleft2.components.add(new Collider(btnleft2));
        gameObjects.add(btnleft2);*/

    }

    public void AddGameObject(GameObject go) {
        gameObjectsToAdd.add(go);
        go.LoadContent(GameView.resources);
    }

    void LoadContent(Resources resources) {


        for (GameObject go : gameObjects) {
            go.LoadContent(resources);
        }
    }

    public static GameWorld getInstance() {
        if (instance == null) {
            instance = new GameWorld();
        }
        return instance;
    }

    boolean Destroy(GameObject go) {
        boolean success = false;
        if (gameObjects.contains(go)) {
            success = gameObjectsToDestroy.add(go);
        }

        return success;
    }

    public void Update(float deltaTime) {

        if (gameObjectsToDestroy.size() > 0) {
            for (GameObject goD : gameObjectsToDestroy) {
                gameObjects.remove(goD);
            }
            gameObjectsToDestroy = new LinkedList<>();
        }
        if (gameObjectsToAdd.size() > 0) {
            for (GameObject goA : gameObjectsToAdd) {
                gameObjects.add(goA);
            }
            gameObjectsToAdd.clear();
        }

        for (GameObject go : gameObjects) {
            go.Update(deltaTime);
        }
        if(isTouched)
        for (GameObject go : gameObjects) {
            go.Controller(touchAxisX, touchAxisY);
        }
    }
    public void Draw(Canvas canvas, Paint paint) {
        for (GameObject go : gameObjects) {
            go.Draw(canvas, paint);
        }
    }
    boolean isTouched;
    float touchAxisX;
    float touchAxisY;
    public void Controller(float axisX, float axisY, boolean isTouched) {
        touchAxisX = axisX;
        touchAxisY = axisY;
        this.isTouched = isTouched;
    }

    public void OnPause() {
        try {
            colThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void OnResume() {
        collisionChecker = new CollisionChecker();
        colThread = new Thread(collisionChecker);
        colThread.start();
    }
}
