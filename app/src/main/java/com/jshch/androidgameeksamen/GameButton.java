package com.jshch.androidgameeksamen;

public class GameButton extends Component implements ControlAble {

    String btnTag;
    Renderer renderer;
    public GameButton(GameObject go, String btnTag) {
        super(go);
        tag = "button";
        this.btnTag = btnTag;
        renderer = (Renderer) go.GetComponent("Renderer");
    }
    boolean IsTouched(float x, float y){
        float height = renderer.bitmap.getHeight();
        float width = renderer.bitmap.getWidth();
        if(x > go.transform.GetPosition().getX() && x < go.transform.GetPosition().getX()+width){
            if(y > go.transform.GetPosition().getY() && y < go.transform.GetPosition().getY()+height){
                return true;
            }
        }
        return false;
    }
    @Override
    public void Controller(float x, float y) {


        if(IsTouched(x,y)){
            switch (btnTag){
                case "left":
                    Vector2 translate = new Vector2(go.getTransform().GetPosition().getX()-10,go.getTransform().GetPosition().getY());
                    go.getTransform().SetPosition(translate);
                    break;
                case "right":
                    translate = new Vector2(go.getTransform().GetPosition().getX()+10,go.getTransform().GetPosition().getY());
                    go.getTransform().SetPosition(translate);
                    break;
            }

        }
    }
}
