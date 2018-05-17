package com.jshch.androidgameeksamen;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.LinkedList;

public class CollisionChecker implements Runnable {
    @Override
    public void run() {
        while (GameWorld.getInstance().Playing) {
            if (GameWorld.getInstance().gameObjects != null) {

                LinkedList<GameObject> ToCheck = new LinkedList<>(GameWorld.getInstance().gameObjects);
                @SuppressLint("UseSparseArrays") HashMap<Integer, GameObject> hasBeenAdded = new HashMap<>(200);
                for (GameObject go : ToCheck) {
                    Collider col = (Collider) go.GetComponent("Collider");

                    if (col != null) {
                        for (GameObject otherGo : ToCheck) {

                            if (go != otherGo) {
                                Collider otherCol = (Collider) otherGo.GetComponent("Collider");
                                if (otherCol != null) {
                                    if (!hasBeenAdded.containsKey(otherGo.hashCode())) {


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
                            hasBeenAdded.put(go.hashCode(), go);
                        }
                    }
                    hasBeenAdded.clear();
                }
            }
        }
    }

    Boolean Contains(LinkedList<GameObject> list, GameObject object) {
        for (GameObject lookAt : list) {
            if (lookAt == object) {
                return true;
            }
        }
        return false;
    }
}
