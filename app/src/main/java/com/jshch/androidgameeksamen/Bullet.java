package com.jshch.androidgameeksamen;

import android.os.Debug;
import android.util.Log;

public class Bullet extends Component implements UpdateAble {

    int blastRadius;
    int damage;

    public Bullet(GameObject go){
        super(go);
        damage = 50;
    }

    @Override
    public void Update(float deltaTime){

        //Move the object in a desired direction with a speed/power


    }

    void Boom(GameObject tank){
        if(tank != null) {
            float distanceFromBlast = tank.getTransform().Position.Distance(this.GetGameObject().getTransform().Position);
            float damageDone = clamp(distanceFromBlast, blastRadius, 0);

            ((Tank) tank.GetComponent("Tank")).TakeDamage((int) damageDone);
        }
        boolean removed = GameWorld.getInstance().Destroy(this.GetGameObject());
        if (!removed) {
            Log.d("message", "Error in destroying gameObject");
        }

    }

    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }
}
