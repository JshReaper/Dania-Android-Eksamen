package com.jshch.androidgameeksamen;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BrowserAdapter extends ArrayAdapter<LobbyInfo> {

    public BrowserAdapter(@NonNull Context context) {
        super(context, 0, NetWorkManager.lobbies);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.list_item, parent, false);
        }

        LobbyInfo currentLobby = NetWorkManager.lobbies.get(position);

        TextView lobby_name = listItem.findViewById(R.id.textView_lobby_name);
        lobby_name.setText(currentLobby.name);

        TextView host_desc = listItem.findViewById(R.id.textView_description);
        host_desc.setText(currentLobby.GetPlayer(0));

        return listItem;
    }
}
