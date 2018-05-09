package com.jshch.androidgameeksamen;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

class LobbyPlayer{
    boolean isHost;
    String name;
    String id;
    String color;
    LobbyPlayer(boolean isHost,String name,String color){

        this.isHost = isHost;
        this.name = name;
        this.color = color;
        id = UUID.randomUUID().toString();
    }
}
class LobbyInfo{
    String name;
    private String creationTime;
    String id;
    LinkedList<LobbyPlayer> players;

    public String GetName(){
        return name;
    }

    LobbyInfo(String id,String name, LinkedList<LobbyPlayer> players){
        this.id = id;
        this.players = players;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
        Date date = new Date();
        creationTime = sdf.format(date);
        this.name = name;
    }
}
