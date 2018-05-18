package com.jshch.androidgameeksamen;

import android.util.Log;

public class Bullet extends Component implements UpdateAble, CollideAble {

    int blastRadius;
    int damage;
    Vector2 direction;

    public Bullet(GameObject go, Vector2 direction, float power) {
        super(go);
        damage = 50;
        this.direction = Vector2.Scale(direction, power);
        tag = "Bullet";
        go.tag = tag;
    }

    @Override
    public void Update(float deltaTime) {

        //Move the object in a desired direction with a speed/power

        GetGameObject().getTransform().Position.Add(Vector2.Scale(direction, deltaTime));
        direction.Add(Vector2.Scale(new Vector2(0, 10), deltaTime));

    }

    void Boom(GameObject tank) {
        if (tank != null) {
            float distanceFromBlast = tank.getTransform().Position.Distance(this.GetGameObject().getTransform().Position);
            float damageDone = clamp(distanceFromBlast, blastRadius, 0);

            ((Tank) tank.GetComponent("Tank")).TakeDamage((int) damageDone);
        }
        boolean removed = GameWorld.getInstance().Destroy(this.GetGameObject());
        if (!removed) {
            Log.d("message", "Error in destroying gameObject");
        }

    }

    void CheckForTanksInRange() {
        GameObject[] gos = new GameObject[2];
        int i = 0;
        for (GameObject go : GameWorld.getInstance().gameObjects) {
            if (go.tag == "tank") {
                gos[0] = go;
                i++;
            }
        }

    }

    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    @Override
    public void OnCollisionEnter(Collider other) {
        if (other.GetGameObject().tag == "ground") {

        }
        if (other.GetGameObject().tag == "tank") {
            Boom(other.GetGameObject());
        }
    }

    @Override
    public void OnCollisionStay(Collider other) {

    }

    @Override
    public void OnCollisionExit(Collider other) {

    }
}
