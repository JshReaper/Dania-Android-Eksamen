package com.jshch.androidgameeksamen;

import java.util.LinkedList;

public class CollisionChecker implements Runnable {
    @Override
    public void run() {
        if(GameWorld.getInstance().Playing){
            if(GameWorld.getInstance().gameObjects != null){
                LinkedList<GameObject> ToCheck =  (LinkedList<GameObject>) GameWorld.getInstance().gameObjects.clone();
                for (GameObject go: ToCheck){
                    Collider col = (Collider) go.GetComponent("Collider");
                    if(col != null){
                        for (GameObject otherGo: ToCheck){
                            if(go != otherGo){
                                Collider otherCol = (Collider) otherGo.GetComponent("Collider");
                                if(otherCol != null)
                                    if(col.InterSectsWidthRect(otherCol)){
                                        go.OnCollisionEnter();
                                        go.OnCollisionStay();
                                    }
                            }
                        }
                    }
                }
            }
        }
    }
}
