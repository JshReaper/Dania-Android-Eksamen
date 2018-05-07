package com.jshch.androidgameeksamen;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

class LobbyInfo{
    String name;
    String creationTime;
    LobbyInfo(String name){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
        Date date = new Date();
        creationTime = sdf.format(date);
        this.name = name;
    }
}
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
public class NetWorkManager {


   private static LinkedList<String> lobbies = new LinkedList<>();
    public void CreateAndJoinLobby(String lobbyName,String playerName){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String uniqueID = UUID.randomUUID().toString();
        LobbyInfo lobbyInfo = new LobbyInfo(lobbyName);
        lobbies.add(uniqueID);
        DatabaseReference lobbyRef = database.getReference("lobbies/"+uniqueID);
        lobbyRef.child("lobby info").setValue(lobbyInfo);

        //add the player
        LinkedList<LobbyPlayer> players = new LinkedList<>();
        players.add(new LobbyPlayer(true,playerName,"white"));
        lobbyRef.child("players").setValue(players);
    }
   public void HalloWorldExample(){
        //setup database instance
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //generate reference to entry in database
        DatabaseReference myRef = database.getReference("message");
        //change value in the referenced entry
        myRef.setValue("Hello, world: jacob");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("message", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("message", "Failed to read value.", error.toException());
            }
        });
    }

}
