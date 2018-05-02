package com.jshch.androidgameeksamen;

abstract public class Component {

    protected GameObject go;

    public Component(GameObject go){
        this.go = go;
    }

    public GameObject GetGameObject(){
        return go;
    }
}
