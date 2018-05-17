package com.jshch.androidgameeksamen;

import android.content.res.Resources;
import android.util.Log;

public class Tank extends Component implements UpdateAble, CollideAble, LoadAble{

    int hp = 100;
    float fuel = 100;
    int speed = 50;
    float power;
    float angle;
    Vector2 turretOffset;
    GameObject turretObject;
    Vector2 gravity;
    boolean onGround;
    Turret myTurret;
    AudioController audio;

    int myImageSizeX;
    int myImageSizeY;
    public Tank(GameObject go, GameObject turret){
        super(go);
        turretObject = turret;
        myTurret = (Turret) turret.GetComponent("Turret");
        tag = "Tank";

    }

    @Override
            public void LoadContent(Resources resources){
        Renderer render = (Renderer) GetGameObject().GetComponent("Renderer");
        if(render != null) {
            myImageSizeX = render.bitmap.getWidth();
            myImageSizeY = render.bitmap.getHeight();
        }
        audio = (AudioController)GetGameObject().GetComponent("AudioController");
    }
boolean first = true;
    Vector2 lastPos = Vector2.Zero();

    //test values
    int xCoord;
    float time;
    @Override
    public void Update(float deltaTime) {
        //apply gravity
        if(!onGround && gravity != null){
            GetGameObject().getTransform().Position = GetGameObject().getTransform().Position.Add(gravity);
            gravity = Vector2.Scale(gravity,1.01f);
        }else if(!onGround){
            gravity = new Vector2(0,2);
        }
        if(xCoord < 2400){

            Log.d("message","" + MapInfoGenerator.HeightFromXcoord(xCoord,0));
            xCoord += 50;

        }
        time += deltaTime;
        if(time >= 5){
            Log.d("",""+deltaTime);
            Log.d("","Firing");
            Fire(new Vector2(50,-50));
            time = 0;
        }
            //set the turret offset to the right position every frame
            turretOffset = new Vector2(GetGameObject().getTransform().Position.getX() + (myImageSizeX / 2),
                    GetGameObject().getTransform().Position.getY() + (myImageSizeY / 10));

        Vector2 pos = turretOffset;
        if(angle <= 90 && angle >= -90){
                turretOffset = turretOffset.Add(new Vector2(0,-myTurret.yOffset));
            }else if(angle > 90 && angle <= 180){
                turretOffset = turretOffset.Add(new Vector2(-myTurret.width,-myTurret.yOffset));
            }
        //if the turret object position is different from the expected position place it correctly
        if(turretOffset != turretObject.getTransform().Position){
            turretObject.getTransform().SetPosition(turretOffset);
        }

        angle += 0.2f;
        SetAngle(angle);
        if(angle > 180){
            angle = 0;
        }


        if(first){
            angle = 10;
            SetAngle(angle);

            first = false;

        }
        lastPos = GetGameObject().getTransform().Position;
    }

    void Fire(Vector2 direction){
        Transform pos = new Transform(turretOffset.Add(Vector2.Scale(direction.Normalized(),20) ),new Vector2(1,1));
        GameObject bullet = new GameObject(pos);
        Renderer render = new Renderer(bullet,R.drawable.bullet);
        Collider col = new Collider(bullet);
        Bullet blt = new Bullet(bullet,direction,power);
        bullet.components.add(blt);
        bullet.components.add(render);
        bullet.components.add(col);
        GameWorld.getInstance().AddGameObject(bullet);


    }
    void Move(Vector2 direction){

        //set the position to the direction we want to move in
        GetGameObject().getTransform().Position.Add(Vector2.Scale(direction,speed));
    }
    void Repair(){

    }
    void SetAngle(float angle){

        this.angle = angle;
        Turret trt = (Turret) turretObject.GetComponent("Turret");
        if(trt != null && turretOffset != null) {
            trt.RotateTurretTo(angle, turretOffset);
        }
    }

    void TakeDamage(int damage){
        hp -= damage;
    }

    @Override
    public void OnCollisionEnter(Collider other){
        Log.d("message","Tag : " + other.GetGameObject().tag);
        if(other.GetGameObject().tag.equals("ground")){
            onGround = true;
            gravity = Vector2.Zero();
        }
    }
    @Override
    public void OnCollisionStay(Collider other){
        if(other.GetGameObject().tag.equals("ground")) {
            Log.d("message", "Collision resets active from the loop");
        }
    }
    @Override
    public void OnCollisionExit(Collider other){
        if(onGround && other.GetGameObject().tag.equals("ground")){
            onGround = !onGround;
            gravity = new Vector2(0,3);
        }
    }


}
