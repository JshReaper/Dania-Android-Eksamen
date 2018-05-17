package com.jshch.androidgameeksamen;

import java.util.ArrayList;

public class GameInfo {
    ArrayList<PlayerInfo> players = new ArrayList<>();
    String id;

    GameInfo(LobbyInfo lobbyInfo) {
        id = lobbyInfo.id;
        for (LobbyPlayer lplayer : lobbyInfo.players) {
            players.add(new PlayerInfo(lplayer.id, lplayer.name, lplayer.color));
        }
    }
}

class PlayerInfo {
    String id;
    String name;
    String color;
    float posX;
    float posY;
    float cannonAngle;
    float powerFromLastShot;
    boolean isAlive;

    PlayerInfo(String id, String name, String color) {
        this.color = color;
        this.id = id;
        this.name = name;
    }
}