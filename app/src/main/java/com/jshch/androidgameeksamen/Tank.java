package com.jshch.androidgameeksamen;

public class Tank extends Component {

    int hp = 100;
    float fuel = 100;
    int speed = 50;
    float power;
    Vector2 angle;

    public Tank(GameObject go){
        super(go);
    }

    void Fire(){

    }
    void Move(Vector2 direction){

        GetGameObject().getTransform().Position.Add(Vector2.Scale(direction,speed));
    }
    void Repair(){

    }
    void SetAngle(Vector2 angle){
        this.angle = angle;
    }

    void TakeDamage(int damage){
        hp -= damage;
    }



}
