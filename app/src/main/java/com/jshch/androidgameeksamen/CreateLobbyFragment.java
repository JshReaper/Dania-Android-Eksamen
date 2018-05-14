package com.jshch.androidgameeksamen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class CreateLobbyFragment extends Fragment {

    Button createLB;
    NetWorkManager netMan;
    EditText lobbyName, playerName;
    Spinner colorSpinner;
    String[]items = new String[]{"White", "Black", "Yellow"};
    String color;

    FragmentTransaction fragmentTransaction;

    public CreateLobbyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netMan = new NetWorkManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_lobby, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        createLB = view.findViewById(R.id.createLB);

        lobbyName = view.findViewById(R.id.lobbyText);
        playerName = view.findViewById(R.id.nameText);
/*
        colorSpinner = view.findViewById(R.id.colorSpinner);*/
    }

    @Override
    public void onResume(){
        super.onResume();


        createLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateButtonEvent();
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*ArrayAdapter<String>adapter = new ArrayAdapter<>(context, 0, items);
        colorSpinner.setAdapter(adapter);
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                color = items[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void CreateButtonEvent(){
        netMan.CreateAndJoinLobby(lobbyName.toString(), playerName.toString(), color);

        fragmentTransaction = getFragmentManager().beginTransaction();
        LobbyFragment lFrag = new LobbyFragment();
        fragmentTransaction.replace(android.R.id.content, lFrag);
        fragmentTransaction.commit();
    }

}
