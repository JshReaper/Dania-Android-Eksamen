package com.jshch.androidgameeksamen;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;


public class BrowserFragment extends Fragment {

    private ListView listV;
    private BrowserAdapter browAdapter;
    private ArrayList<LobbyInfo> lobbies;
    NetWorkManager netMan;

    public BrowserFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netMan = new NetWorkManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_browser, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        listV = getView().findViewById(R.id.lobbyListView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        RefreshLobbies();

        browAdapter = new BrowserAdapter(context, lobbies);
        if(lobbies != null)
        listV.setAdapter(browAdapter);
    }

    private void RefreshLobbies(){
        if (NetWorkManager.LobbyLoaded){
            lobbies = null;
            lobbies.addAll(NetWorkManager.lobbies);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();


    }

}
