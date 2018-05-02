package com.jshch.androidgameeksamen;

public class Component {

    private GameObject go;

    protected Component(GameObject go){
        SetGameObject(go);
    }

    protected void SetGameObject(GameObject go){
        this.go = go;
    }

    protected GameObject GetGameObject(){
        return go;
    }
}
