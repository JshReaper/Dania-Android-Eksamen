package com.jshch.androidgameeksamen;

import java.io.Serializable;
import java.util.ArrayList;

public class GameInfo implements Serializable {
    private ArrayList<PlayerInfo> players = new ArrayList<>();
    private String id;
    GameInfo(){
        //do not remove
    }

    GameInfo(LobbyInfo lobbyInfo) {
        id = lobbyInfo.getId();
        for (LobbyPlayer lplayer : lobbyInfo.getPlayers()) {
            players.add(new PlayerInfo(lplayer.getId(), lplayer.getName(), lplayer.getColor()));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<PlayerInfo> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<PlayerInfo> players) {
        this.players = players;
    }
}

class PlayerInfo implements Serializable{
   private String id;
   private String name;
   private String color;
   private float posX;
   private float posY;
   private float cannonAngle;
   private float powerFromLastShot;
   private boolean isAlive;
   PlayerInfo(){
       //do not remove
   }
    PlayerInfo(String id, String name, String color) {
        this.color = color;
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   public boolean getAlive(){
        return isAlive;
    }
    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosX() {
        return posX;
    }


    public float getCannonAngle() {
        return cannonAngle;
    }
    public void setCannonAngle(float cannonAngle) {
        this.cannonAngle = cannonAngle;
    }


    public void setPosY(float posY) {
        this.posY = posY;
    }
    public float getPosY() {
        return posY;
    }





    public float getPowerFromLastShot() {
        return powerFromLastShot;
    }
    public void setPowerFromLastShot(float powerFromLastShot) {
        this.powerFromLastShot = powerFromLastShot;
    }


}