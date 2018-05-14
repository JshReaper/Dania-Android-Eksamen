package com.jshch.androidgameeksamen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.sql.Time;
import java.util.ArrayList;


public class Terrain extends Component implements UpdateAble{

    int NrBoxes = 20;
    ArrayList<TerrainBox> boxes;
    Bitmap BG_img, box_img;
    boolean addColliders;
    public Terrain(GameObject go, Bitmap BG_img, Bitmap box_img) { super(go);
        this.BG_img = BG_img;
        this.box_img = box_img;
        tag = "Terrain";
        boxes = new ArrayList<>();

        for (int i = 0; i < NrBoxes; i++){
            //1000 y ??
            boxes.add(new TerrainBox(new GameObject (new Transform(new Vector2(i * 10, 1000), 1f)), box_img));
            boxes.get(i).go.components.add(new Renderer(boxes.get(i).go,R.drawable.kappa));
            GameWorld.getInstance().AddGameObject(boxes.get(i).go);

        }

    }
    @Override
    public void Update(float deltaTime) {
        if(addColliders) {
            AddColliders();
        }
    }
    void AddColliders(){
        for (TerrainBox box : boxes){
            Collider col =(Collider) box.go.GetComponent("Collider");
            if(col != null){
                box.go.components.remove(col);
            }
            boolean oneAboveMe = false;
            for (TerrainBox other: boxes){

                if(box.go.transform.GetPosition().getX() == other.go.transform.GetPosition().getX()){
                    if(box.go.transform.GetPosition().getY() > other.go.transform.GetPosition().getY()){
                        oneAboveMe = true;
                        break;
                    }
                }
            }
            if(!oneAboveMe){
                box.go.components.add(new Collider(box.go));
            }
        }

    }
}
