package com.jshch.androidgameeksamen;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

class CollThread implements Runnable {
    private ArrayList<Collider> colliders;
    private ArrayList<Collider> others;

    CollThread(ArrayList<Collider> colliders, ArrayList<Collider> others) {
        this.colliders = colliders;
        this.others = others;
    }

    @Override
    public void run() {
        for (Collider collider : colliders) {
            for (Collider otherCol : others) {
                if (collider.InterSectsWidthRect(otherCol)) {
                    if (!collider.OtherColliders.contains(otherCol)) {
                        collider.go.OnCollisionEnter(otherCol);
                        otherCol.GetGameObject().OnCollisionEnter(collider);
                        collider.OtherColliders.add(otherCol);
                        otherCol.OtherColliders.add(collider);
                    } else {
                        collider.go.OnCollisionStay(otherCol);
                        otherCol.GetGameObject().OnCollisionStay(collider);
                    }
                } else {
                    if (collider.OtherColliders.contains(otherCol)) {
                        collider.go.OnCollisionExit(otherCol);
                        otherCol.GetGameObject().OnCollisionExit(collider);
                        collider.OtherColliders.remove(otherCol);
                        otherCol.OtherColliders.remove(collider);
                    }
                }
            }
        }
    }
}

public class CollisionChecker implements Runnable {
    @Override
    public void run() {
        while (GameWorld.getInstance().Playing) {
            if (GameWorld.getInstance().gameObjects != null) {

                LinkedList<GameObject> ToCheck = new LinkedList<>(GameWorld.getInstance().gameObjects);
                ArrayList<Collider> colliders = new ArrayList<>();
                for (GameObject go : ToCheck) {
                    Collider col = (Collider) go.GetComponent("Collider");

                    if (col != null) {
                        colliders.add(col);
                    }
                }
                ArrayList<ArrayList<Collider>> colliderLists = new ArrayList<>();
                if (colliders.size() > 50) {
                    int elementsLeft = colliders.size();
                    int currentIndex = 0;
                    while (elementsLeft > 50) {
                        ArrayList<Collider> tColls = new ArrayList<>();
                        for (int i = 0; i < 50; i++) {
                            tColls.add(colliders.get(currentIndex));
                            currentIndex++;
                            elementsLeft--;
                        }
                        colliderLists.add(tColls);
                    }
                    if (elementsLeft > 0) {
                        //TODO add the rest
                        ArrayList<Collider> tColls = new ArrayList<>();
                        int forloop = elementsLeft;
                        for (int i = 0; i < forloop; i++) {
                            tColls.add(colliders.get(currentIndex));
                            currentIndex++;
                            elementsLeft--;
                        }
                        colliderLists.add(tColls);

                    }
                    int check = elementsLeft;
                }
                for (ArrayList<Collider> collsToCheck : colliderLists) {
                    CollThread collThread = new CollThread(collsToCheck, colliders);
                    Thread t = new Thread(collThread);
                    t.start();
                }
              /*  try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
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
