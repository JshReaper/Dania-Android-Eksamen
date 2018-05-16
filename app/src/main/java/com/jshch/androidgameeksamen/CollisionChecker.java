package com.jshch.androidgameeksamen;

import java.util.LinkedList;

public class CollisionChecker implements Runnable {
    @Override
    public void run() {
        if(GameWorld.getInstance().Playing){
            if(GameWorld.getInstance().gameObjects != null){

                LinkedList<GameObject> ToCheck = GameWorld.getInstance().gameObjects;
                LinkedList<GameObject> hasBeenChecked = new LinkedList<>();
                for (GameObject go: ToCheck){
                    Collider col = (Collider) go.GetComponent("Collider");

                    if(col != null){
                        for (GameObject otherGo: ToCheck){

                            if(go != otherGo){
                                Collider otherCol = (Collider) otherGo.GetComponent("Collider");
                                if(otherCol != null){
                                    //if(!hasBeenChecked.contains(otherGo)) {


                                        if (col.InterSectsWidthRect(otherCol)) {
                                            if (!col.OtherColliders.contains(otherCol)) {
                                                go.OnCollisionEnter(otherCol);
                                                otherCol.GetGameObject().OnCollisionEnter(col);
                                                col.OtherColliders.add(otherCol);
                                                otherCol.OtherColliders.add(col);
                                            } else {
                                                go.OnCollisionStay(otherCol);
                                                otherCol.GetGameObject().OnCollisionStay(col);
                                            }
                                        } else {
                                            if (col.OtherColliders.contains(otherCol)) {
                                                go.OnCollisionExit(otherCol);
                                                otherCol.GetGameObject().OnCollisionExit(col);
                                                col.OtherColliders.remove(otherCol);
                                                otherCol.OtherColliders.remove(col);
                                            }
                                        }
                                    }
                                }
                            }
                       // }
                        //hasBeenChecked.add(go);
                    }

                }
               // hasBeenChecked.clear();
            }
        }
    }
}
