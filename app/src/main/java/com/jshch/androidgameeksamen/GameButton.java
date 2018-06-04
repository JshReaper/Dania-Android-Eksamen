package com.jshch.androidgameeksamen;

import android.arch.core.util.Function;
import android.util.Log;

import java.util.LinkedList;
import java.util.concurrent.Callable;

public class GameButton extends Component implements ControlAble {

    String btnTag;
    Renderer renderer;
   public LinkedList<IButtonListener> subscribers = new LinkedList<>();

    public GameButton(GameObject go, String btnTag) {
        super(go);
        tag = "GameButton";
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
    public void Controller(float x, float y, boolean isTouched) {

        if (ButtonPressed(x, y) && isTouched) {
            for (IButtonListener listener : subscribers){
                listener.ButtonClickedEvent();
            }

        }
    }
}
