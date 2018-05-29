package com.jshch.androidgameeksamen;

import android.util.Log;

import java.util.Vector;

public class TankController extends Component implements UpdateAble, ControlAble {


    boolean myTurn = true;
    Vector2 firstPos;
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
            if(firstPos != null){
                    Vector2 temp = firstPos.Subtract(myTank.turretOffset);
                    angle = temp.AngleToVector2D(new Vector2(10, 0));
                Log.d("MSG","TEMP vector is:" + angle);
                    if (angle < 0) {
                        angle = 0;
                    } else if (angle > 180) {
                        angle = 180;
                    }
                    myTank.SetAngle(angle);

            }
            if(fire && firstPos != null){
                myTank.Fire(firstPos.Subtract(myTank.turretOffset));
                myTurn = false;
            }
        }

    }

    @Override
    public void Controller(float x, float y, boolean isToutched){

        if(isToutched) {

                firstPos = new Vector2(x, y);
            Log.w("firstpos: ", ""+ firstPos.getX()+","+firstPos.getY());
        }
    }

}
