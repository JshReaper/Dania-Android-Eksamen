package com.jshch.androidgameeksamen;

public class Tank extends Component implements UpdateAble{

    int hp = 100;
    float fuel = 100;
    int speed = 50;
    float power;
    Vector2 angle;
    Vector2 turretOffset;
    GameObject turretObject;

    int myImageSizeX;
    int myImageSizeY;
    public Tank(GameObject go){
        super(go);
        Renderer render = (Renderer) GetGameObject().GetComponent("Renderer");
        myImageSizeX = render.bitmap.getWidth();
        myImageSizeY = render.bitmap.getHeight();
    }

    @Override
    public void Update(float deltaTime) {
        //set the turret offset to the right position every frame
        turretOffset = new Vector2(GetGameObject().getTransform().Position.getX() + myImageSizeX / 2,
                GetGameObject().getTransform().Position.getY() + myImageSizeY / 3);


        //if the turret object position is different from the expected position place it correctly
        if(turretOffset != turretObject.getTransform().Position){
            turretObject.getTransform().SetPosition(turretOffset);
        }
    }

    void Fire(){

    }
    void Move(Vector2 direction){

        //set the position to the direction we want to move in
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
