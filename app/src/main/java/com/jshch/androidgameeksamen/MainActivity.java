package com.jshch.androidgameeksamen;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends FragmentActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragTransaction;
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // already signed in
        } else {
            startActivityForResult(
                    // Get an instance of AuthUI based on the default app
                    AuthUI.getInstance().createSignInIntentBuilder().build(),
                    RC_SIGN_IN);

        }
        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!= null) {
                    NetWorkManager nm = new NetWorkManager();
                    nm.LoadLobby();
                    auth.removeAuthStateListener(this);
                }
            }
        });


        fragmentManager = getSupportFragmentManager();
        fragTransaction = fragmentManager.beginTransaction();


        MenuFragment mFrag = new MenuFragment();

        fragTransaction.replace(android.R.id.content, mFrag);
        fragTransaction.addToBackStack("MENU");
        fragTransaction.commit();
    }


}
