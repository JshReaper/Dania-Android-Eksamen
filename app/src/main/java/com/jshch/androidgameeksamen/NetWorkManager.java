package com.jshch.androidgameeksamen;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;


public class NetWorkManager {

   private static ArrayList<LobbyInfo> lobbies = new ArrayList<>();
    public void CreateAndJoinLobby(final String lobbyName,final String playerName){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference lobbyRef = database.getReference("lobbies/");
        lobbyRef.addValueEventListener( new ValueEventListener()  {
            @SuppressWarnings("unchecked")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                lobbies.addAll((ArrayList<LobbyInfo>)dataSnapshot.getValue());
                lobbyRef.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //add the player
        LinkedList<LobbyPlayer> players = new LinkedList<>();
        players.add(new LobbyPlayer(true,playerName,"white"));
        String uniqueID = UUID.randomUUID().toString();
        LobbyInfo lobbyInfo = new LobbyInfo(uniqueID,lobbyName,players);
        lobbies.add(lobbyInfo);
        lobbyRef.setValue(lobbies);
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
