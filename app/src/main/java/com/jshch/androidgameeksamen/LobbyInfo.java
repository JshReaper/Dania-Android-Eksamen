package com.jshch.androidgameeksamen;

import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

class LobbyPlayer{
    boolean isHost;
    String name;
    String id;
    String color;

    public String GetPlayerName(){
        return name;
    }

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

    public String GetId(){
        return id;
    }

    public String GetPlayer(@Nullable int player){
        if ((player > 1 || player == 1) && players.size() > 1) {
            return players.get(1).GetPlayerName();
        }else if (player < 0){
            return players.get(0).GetPlayerName();
        }
        return players.get(0).GetPlayerName();


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
