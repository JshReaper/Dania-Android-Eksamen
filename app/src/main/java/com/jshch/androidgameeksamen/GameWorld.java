package com.jshch.androidgameeksamen;

public class GameWorld {
   private static GameWorld instance;
   protected GameWorld(){

   }

    public static GameWorld getInstance() {
       if(instance == null){
           instance = new GameWorld();
       }
       return instance;
    }
}
