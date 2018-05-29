package com.jshch.androidgameeksamen;

import android.util.Log;

import java.util.LinkedList;

public class GameButton extends Component implements ControlAble {

    String btnTag;
    Renderer renderer;
   public LinkedList<Boolean> subscribers = new LinkedList<>();

    public GameButton(GameObject go, String btnTag) {
        super(go);
        tag = "button";
        this.btnTag = btnTag;
        renderer = (Renderer) go.GetComponent("Renderer");
    }

    boolean ButtonPressed(float x, float y) {
        if(renderer == null){
            return false;
        }
        float height = renderer.bitmap.getHeight();
        float width = renderer.bitmap.getWidth();
        if (x > go.transform.GetPosition().getX() && x < go.transform.GetPosition().getX() + width) {
            if (y > go.transform.GetPosition().getY() && y < go.transform.GetPosition().getY() + height) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void Controller(float x, float y, boolean isToutched) {

        for (Boolean bool : subscribers  ){
            bool = ButtonPressed(x,y);
        }
        if (ButtonPressed(x, y)) {
            switch (btnTag) {
                case "left":
                    Vector2 translate = new Vector2(go.getTransform().GetPosition().getX() - 10, go.getTransform().GetPosition().getY());
                    go.getTransform().SetPosition(translate);
                    break;
                case "right":
                    translate = new Vector2(go.getTransform().GetPosition().getX() + 10, go.getTransform().GetPosition().getY());
                    go.getTransform().SetPosition(translate);
                    break;



            }

        }
    }
}
