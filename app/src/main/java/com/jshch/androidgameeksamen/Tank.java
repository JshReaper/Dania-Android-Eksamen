package com.jshch.androidgameeksamen;

public class Tank extends Component implements UpdateAble, CollideAble{

    int hp = 100;
    float fuel = 100;
    int speed = 50;
    float power;
    float angle;
    Vector2 turretOffset;
    GameObject turretObject;
    Vector2 gravity;
    boolean onGround;

    int myImageSizeX;
    int myImageSizeY;
    public Tank(GameObject go, GameObject turret){
        super(go);
        turretObject = turret;
        Renderer render = (Renderer) GetGameObject().GetComponent("Renderer");
        myImageSizeX = render.bitmap.getWidth();
        myImageSizeY = render.bitmap.getHeight();
    }
    Vector2 lastPos = Vector2.Zero();
    @Override
    public void Update(float deltaTime) {
        //apply gravity
        if(!onGround){
            GetGameObject().getTransform().Position.Add(Vector2.Scale(gravity,deltaTime));
            gravity = Vector2.Scale(gravity,1.01f);
        }

        if(lastPos != GetGameObject().getTransform().Position) {
            //set the turret offset to the right position every frame
            turretOffset = new Vector2(GetGameObject().getTransform().Position.getX() + myImageSizeX / 2,
                    GetGameObject().getTransform().Position.getY() + myImageSizeY / 3);
        }

        //if the turret object position is different from the expected position place it correctly
        if(turretOffset != turretObject.getTransform().Position){
            turretObject.getTransform().SetPosition(turretOffset);
        }
        lastPos = GetGameObject().getTransform().Position;
    }

    void Fire(Vector2 direction){
        Transform pos = new Transform(turretOffset.Add(Vector2.Scale(direction.Normalized(),20) ),new Vector2(1,1));
        GameObject bullet = new GameObject(pos);
        Bullet blt = new Bullet(bullet);
        bullet.components.add(blt);

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
        trt.RotateTurretTo(angle,turretOffset);
    }

    void TakeDamage(int damage){
        hp -= damage;
    }

    @Override
    public void OnCollisionEnter(Collider other){

        if(other.GetGameObject().tag == "ground"){
            onGround = true;
            gravity = Vector2.Zero();
        }
    }
    @Override
    public void OnCollisionStay(Collider other){

    }
    @Override
    public void OnCollisionExit(Collider other){
        if(onGround && other.GetGameObject().tag == "ground"){
            onGround = !onGround;
            gravity = new Vector2(0,10);
        }
    }


}
