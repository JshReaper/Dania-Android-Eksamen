package com.jshch.androidgameeksamen;

public class Transform {

    public Vector2 Position;

    Vector2 GetPosition() {
        return Position;
    }

    Vector2 scale;


    public Transform(Vector2 position, Vector2 scale) {
        this.scale = scale;
        this.Position = position;
    }

    public Transform(Vector2 position, float scale) {
        this.scale = new Vector2(scale, scale);
        this.Position = position;
    }


    void SetPosition(Vector2 position) {
        this.Position = position;
    }

}
