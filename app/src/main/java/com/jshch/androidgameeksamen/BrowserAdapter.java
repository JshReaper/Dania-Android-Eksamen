package com.jshch.androidgameeksamen;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BrowserAdapter extends ArrayAdapter<LobbyInfo> {

    private ArrayList<LobbyInfo> lobbies;

    public BrowserAdapter(@NonNull Context context, ArrayList<LobbyInfo> lobbies) {
        super(context, 0, lobbies);
        this.lobbies = lobbies;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.list_item,parent,false);

        LobbyInfo currentLobby = lobbies.get(position);

        TextView lobby_name = (TextView) listItem.findViewById(R.id.textView_lobby_name);
        lobby_name.setText(currentLobby.name);

        TextView host_name = (TextView) listItem.findViewById(R.id.textView_host);
        host_name.setText(currentLobby.GetPlayer(0));

        return listItem;
    }
}
