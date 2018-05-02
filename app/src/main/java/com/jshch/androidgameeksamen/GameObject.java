package com.jshch.androidgameeksamen;

import java.util.LinkedList;

public class GameObject {

    LinkedList<Component> components;
    private Transform transform;
    Transform getTransform(){
        return transform;
    }
    GameObject(Transform transform){
        this.transform = transform;
        components = new LinkedList<>();
    }
    Component GetComponent(String component){
        for (Component c : components){
            if(c.toString() == component){
                return c;
            }
        }
        return null;
    }
    public void LoadContent(){
        for (Component c : components){
            if(c instanceof LoadAble){
                ((LoadAble) c).LoadContent();
            }
        }
    }

    public void Update(){
        for (Component c : components){
            if(c instanceof UpdateAble){
                ((UpdateAble) c).Update();
            }
        }
    }

    public void Draw(){
        for (Component c : components){
            if(c instanceof DrawAble){
                ((DrawAble) c).Draw();
            }
        }
    }

    public void OnCollisionEnter(Collider other){
        for (Component c : components){
            if(c instanceof CollideAble){
                ((CollideAble) c).OnCollisionEnter(other);
            }
        }
    }

    public void OnCollisionStay(Collider other){
        for (Component c : components){
            if(c instanceof CollideAble){
                ((CollideAble) c).OnCollisionStay(other);
            }
        }
    }

    public void OnCollisionExit(Collider other){
        for (Component c : components){
            if(c instanceof CollideAble){
                ((CollideAble) c).OnCollisionExit(other);
            }
        }
    }
}
