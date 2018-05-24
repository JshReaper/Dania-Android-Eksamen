package com.jshch.androidgameeksamen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class LobbyFragment extends Fragment {

    String[] player;
    TextView player1, player2;

    public LobbyFragment(String playerName, int player) {
        this.player[player] = playerName;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        player1 = (TextView) getActivity().findViewById(R.id.player1_name);
        player2 = (TextView) getActivity().findViewById(R.id.player2_name);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lobby, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
