package com.jshch.androidgameeksamen;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class BrowserFragment extends Fragment {

    private ListView listV;
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
        listV = getView().findViewById(R.id.listView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        RefreshLobbies();

       ArrayAdapter arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, NetWorkManager.lobbies);
        listV.setAdapter(arrAdapter);

    }

    private void RefreshLobbies(){
        while(!NetWorkManager.LobbyLoaded){ }


    }


    @Override
    public void onDetach() {
        super.onDetach();


    }

}
