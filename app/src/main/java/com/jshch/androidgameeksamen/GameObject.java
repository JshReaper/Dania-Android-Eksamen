package com.jshch.androidgameeksamen;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.LinkedList;

public class GameObject {

    public String tag;
    LinkedList<Component> components;
    Transform transform;


    Transform getTransform() {
        return transform;
    }

    GameObject(Transform transform) {
        this.transform = transform;
        components = new LinkedList<>();
    }

    Component GetComponent(String component) {
        for (Component c : components) {
            if (c.tag == component) {
                return c;
            }
        }
        return null;
    }

    public void LoadContent(Resources resources) {
        for (Component c : components) {
            if (c instanceof LoadAble) {
                ((LoadAble) c).LoadContent(resources);
            }
        }
    }

    public void Update(float deltaTime) {
        for (Component c : components) {
            if (c instanceof UpdateAble) {
                ((UpdateAble) c).Update(deltaTime);
            }
        }
    }

    public void Draw(Canvas canvas, Paint paint) {
        for (Component c : components) {
            if (c instanceof DrawAble) {
                ((DrawAble) c).Draw(canvas, paint);
            }
        }
    }

    public void OnCollisionEnter(Collider other) {
        for (Component c : components) {
            if (c instanceof CollideAble) {
                ((CollideAble) c).OnCollisionEnter(other);
            }
        }
    }

    public void OnCollisionStay(Collider other) {
        for (Component c : components) {
            if (c instanceof CollideAble) {
                ((CollideAble) c).OnCollisionStay(other);
            }
        }
    }

    public void OnCollisionExit(Collider other) {
        for (Component c : components) {
            if (c instanceof CollideAble) {
                ((CollideAble) c).OnCollisionExit(other);
            }
        }
    }

    public void Controller(float axisX, float axisY) {
        for (Component c : components) {
            if (c instanceof ControlAble) {
                ((ControlAble) c).Controller(axisX, axisY);
            }
        }
    }
}
