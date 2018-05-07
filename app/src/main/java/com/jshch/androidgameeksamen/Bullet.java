package com.jshch.androidgameeksamen;

import android.os.Debug;
import android.util.Log;

public class Bullet extends Component implements UpdateAble {

    int blastRadius;
    int damage;
    Vector2 direction;

    public Bullet(GameObject go, Vector2 direction, float power){
        super(go);
        damage = 50;
        this.direction = Vector2.Scale(direction,power);
    }

    @Override
    public void Update(float deltaTime){

        //Move the object in a desired direction with a speed/power

        GetGameObject().getTransform().Position.Add(Vector2.Scale(direction,deltaTime));
        direction.Add(Vector2.Scale(new Vector2(0,10), deltaTime));

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
