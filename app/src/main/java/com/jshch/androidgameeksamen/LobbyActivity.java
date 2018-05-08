package com.jshch.androidgameeksamen;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

public class LobbyActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetWorkManager nm = new NetWorkManager();
        nm.LoadLobby();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();


        BrowserFragment browserFragment = new BrowserFragment();

        fragTransaction.replace(android.R.id.content, browserFragment);
        fragTransaction.commit();


    }
}
