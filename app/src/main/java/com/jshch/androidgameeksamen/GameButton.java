package com.jshch.androidgameeksamen;

public class GameButton extends Component implements ControlAble {

    String btnTag;

    public GameButton(GameObject go, String btnTag) {
        super(go);
        tag = "button";
        this.btnTag = btnTag;
    }

    @Override
    public void Controller(float x, float y) {
        if(btnTag.equals("test")){
            Vector2 translate = new Vector2(go.getTransform().GetPosition().getX()+10,go.getTransform().GetPosition().getY());
            go.getTransform().SetPosition(translate);
        }
    }
}
