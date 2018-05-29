package com.jshch.androidgameeksamen;

import java.util.Vector;

public class TankController extends Component implements UpdateAble, ControlAble {


    boolean myTurn = true;
    Vector2 firstPos;
    Vector2 secondPos;
    Tank myTank;
    boolean toutch;
    boolean fire = false;

    public TankController(GameObject go){
        super(go);
        myTank = (Tank) GetGameObject().GetComponent("Tank");
    }
    float angle;
    @Override
    public void Update(float deltaTime){
        if(myTurn) {
            if (firstPos != null && secondPos != null) {
                if (firstPos.getX() != secondPos.getX() && firstPos.getY() != secondPos.getY()) {
                    Vector2 angled = secondPos.Subtract(firstPos);
                    angle = angled.AngleToVector2D(new Vector2(1, 0));
                    if (angle < 0) {
                        angle = 0;
                    } else if (angle > 180) {
                        angle = 180;
                    }
                    myTank.SetAngle(angle);
                }
            }
            if(fire){
                myTank.Fire(secondPos.Subtract(firstPos));
                myTurn = false;
            }
        }

    }

    @Override
    public void Controller(float x, float y, boolean isToutched){
        if(!toutch && isToutched){
            firstPos = null;
            secondPos = null;
        }
        toutch = isToutched;
        if(isToutched) {
            if (firstPos == null) {
                firstPos = new Vector2(x, y);
            } else if (secondPos == null) {
                secondPos = new Vector2(x, y);
            } else {
                secondPos.setX(x);
                secondPos.setY(y);
            }
        }
    }

}
