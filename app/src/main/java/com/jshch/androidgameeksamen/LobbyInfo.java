package com.jshch.androidgameeksamen;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

class LobbyPlayer implements Serializable {
    private boolean isHost;
    private String name;
    private String id;
    private String color;
    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setHost(boolean host) {
        isHost = host;
    }


    public String getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public boolean getHost() {
        return isHost;
    }
    LobbyPlayer(){
        //do not remove
    }

    LobbyPlayer(boolean isHost, String name, String color) {

        this.isHost = isHost;
        this.name = name;
        this.color = color;
        id = UUID.randomUUID().toString();
    }


}

class LobbyInfo implements Serializable {
    private String name;
    private String description;
    private String creationTime;
    private String id;
    private ArrayList<LobbyPlayer> players;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<LobbyPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<LobbyPlayer> players) {
        this.players = players;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String GetPlayer(@Nullable int player) {
        if ((player > 1 || player == 1) && players.size() > 1) {
            return players.get(1).getName();
        } else if (player < 0) {
            return players.get(0).getName();
        }
        return players.get(0).getName();


    }
    LobbyInfo(){
        //do not remove
    }
    LobbyInfo(String id, String name, String desc, ArrayList<LobbyPlayer> players) {
        this.id = id;
        this.players = players;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
        Date date = new Date();
        creationTime = sdf.format(date);
        this.name = name;
        this.description = desc;
    }
}
