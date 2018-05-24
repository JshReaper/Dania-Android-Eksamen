package com.jshch.androidgameeksamen;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class BrowserFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView listV;
    private BrowserAdapter browserAdapter;
    NetWorkManager netMan;

    public BrowserFragment() {
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        listV = view.findViewById(R.id.lobbyListView);
        RefreshLobbies();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        browserAdapter = new BrowserAdapter(context);

    }

    private void RefreshLobbies() {
        if (NetWorkManager.LobbyLoaded) {
            if (NetWorkManager.lobbies != null)
                listV.setAdapter(browserAdapter);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();


    }

    @Override
    public void onRefresh() {
        RefreshLobbies();

    }
}
