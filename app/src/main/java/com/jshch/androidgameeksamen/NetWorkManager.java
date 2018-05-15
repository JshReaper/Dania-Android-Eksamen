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

    public static ArrayList<LobbyInfo> lobbies = new ArrayList<>();
    public static boolean LobbyLoaded;
    public static LobbyInfo MyActiveLobby;
    public void LoadLobby(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference lobbyRef = database.getReference("lobbies/");
        lobbyRef.addValueEventListener( new ValueEventListener()  {
            @SuppressWarnings("unchecked")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lobbies =(ArrayList<LobbyInfo>)dataSnapshot.getValue();
                LobbyLoaded = true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("message", "Failed to read value.", databaseError.toException());
            }
        });
    }

    public void CreateAndJoinLobby(String lobbyName, String playerName, String color){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference lobbyRef = database.getReference("lobbies/");

        //add the player
        LinkedList<LobbyPlayer> players = new LinkedList<>();
        players.add(new LobbyPlayer(true,playerName,color));

        //generate random ID
        String uniqueID = UUID.randomUUID().toString();

        //add the lobby
        LobbyInfo lobbyInfo = new LobbyInfo(uniqueID,lobbyName,players);
        MyActiveLobby = lobbyInfo;
        lobbies.add(lobbyInfo);

        //update database
        lobbyRef.setValue(lobbies);
    }

    public void JoinLobby(String id, String name,String color){
        for (LobbyInfo lobby : lobbies){
            //connect to lobby
            if(lobby.id.equals(id)){
                LobbyPlayer player = new LobbyPlayer(false,name,color);
                lobby.players.add(player);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference lobbyRef = database.getReference("lobbies/");
                //update database
                lobbyRef.setValue(lobbies);
                MyActiveLobby = lobby;
            }
        }
    }

    public boolean StartGame(){
        if(MyActiveLobby.players.size() == 2){
            GameInfo gameInfo = new GameInfo(MyActiveLobby);
            //setup database instance
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference gameRef = database.getReference("Games/" + gameInfo.id);

            gameRef.setValue(gameInfo);
            return true;
        }
        return false;
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
