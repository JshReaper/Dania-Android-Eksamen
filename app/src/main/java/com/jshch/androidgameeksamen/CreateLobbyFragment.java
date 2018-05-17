package com.jshch.androidgameeksamen;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class CreateLobbyFragment extends Fragment {

    Button createLB;
    NetWorkManager netMan;
    EditText lobbyName, playerName, lobbyDesc;

    Spinner colorSpinner;

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

        lobbyName = view.findViewById(R.id.lobby_name);
        lobbyDesc = view.findViewById(R.id.lobby_desc);
        playerName = view.findViewById(R.id.player_name);


        colorSpinner = view.findViewById(R.id.color_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext().getApplicationContext(), R.array.colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);
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



    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void CreateButtonEvent(){
        netMan.CreateAndJoinLobby(lobbyName.getText().toString(), playerName.getText().toString(), lobbyDesc.getText().toString(), colorSpinner.getSelectedItem().toString());

        fragmentTransaction = getFragmentManager().beginTransaction();
        LobbyFragment lFrag = new LobbyFragment();
        fragmentTransaction.replace(android.R.id.content, lFrag);
        fragmentTransaction.commit();
    }

}
