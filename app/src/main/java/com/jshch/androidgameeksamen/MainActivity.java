package com.jshch.androidgameeksamen;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;


public class MainActivity extends FragmentActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NetWorkManager nm = new NetWorkManager();
        nm.LoadLobby();

        fragmentManager = getSupportFragmentManager();
        fragTransaction = fragmentManager.beginTransaction();


        MenuFragment mFrag = new MenuFragment();

        fragTransaction.replace(android.R.id.content, mFrag);
        fragTransaction.addToBackStack("MENU");
        fragTransaction.commit();
    }


}
