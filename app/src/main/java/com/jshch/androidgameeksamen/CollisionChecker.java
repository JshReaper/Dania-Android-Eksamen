package com.jshch.androidgameeksamen;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

class CollThread implements Runnable {
    private ArrayList<Collider> colliders;
    private ArrayList<Collider> others;
    int id;
    CollThread(ArrayList<Collider> colliders, ArrayList<Collider> others, int id) {
        this.id = id;
        this.colliders = colliders;
        this.others = others;
    }

    @Override
    public void run() {
      //  Log.w("time taken: ",""+ System.currentTimeMillis() + " my objects: " + colliders.size() + " My ID " + id);

        @SuppressLint("UseSparseArrays") HashMap<Integer, GameObject> hasBeenAdded = new HashMap<>(colliders.size());
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
       // Log.w("time taken: ",""+ System.currentTimeMillis() + " my objects: " + colliders.size() + " My ID " + id);
    }
}

public class CollisionChecker implements Runnable {
    @Override
    public void run() {
        int threadCount = 0;
        while (GameWorld.getInstance().Playing) {
            if (GameWorld.getInstance().gameObjects != null) {
                try {


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
                        //add the rest
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
                }else{
                    colliderLists.add(colliders);
                }
                ArrayList<Thread> threads = new ArrayList<>();
                for (ArrayList<Collider> collsToCheck : colliderLists) {

                    CollThread collThread = new CollThread(collsToCheck, colliders, threadCount);
                    Thread t = new Thread(collThread);
                    threads.add(t);
                    t.start();
                    threadCount++;

                }
                for (Thread t : threads){
                    try {
                        if(t.isAlive()){
                        t.join();
                        }
                        else {
                            threadCount--;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                threads.clear();
                threadCount = 0;
              /*  try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                }
                catch (Exception e){
                    Log.e("list crash thingy: ",e.getMessage());
                }
            }
        }
    }
}
