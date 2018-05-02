package com.jshch.androidgameeksamen;

import java.util.LinkedList;

public class GameObject {

    LinkedList<Component> components;
    GameObject(){
        components = new LinkedList<>();
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
    public void OnCollisionEnter(){
        for (Component c : components){
            if(c instanceof CollideAble){
                ((CollideAble) c).OncollisionEnter();
            }
        }
    }
    public void OnCollisionStay(){
        for (Component c : components){
            if(c instanceof CollideAble){
                ((CollideAble) c).OncollisionStay();
            }
        }
    }
    public void OnCollisionExit(){
        for (Component c : components){
            if(c instanceof CollideAble){
                ((CollideAble) c).OnCollisionExit();
            }
        }
    }
}
