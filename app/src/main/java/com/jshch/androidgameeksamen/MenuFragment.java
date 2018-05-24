package com.jshch.androidgameeksamen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class MenuFragment extends Fragment {

    FragmentTransaction fragmentTransaction;
    Button joinB, createB, exitB, playB;
    private static final int RC_SIGN_IN = 123;


    public MenuFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // already signed in
        } else {
            startActivityForResult(
                    // Get an instance of AuthUI based on the default app
                    AuthUI.getInstance().createSignInIntentBuilder().build(),
                    RC_SIGN_IN);
        }

        //game start flow structure test
      /*
        NetWorkManager netWorkManager = new NetWorkManager();
        netWorkManager.CreateAndJoinLobby("testLobby", "Player1", "test Description", "white");
        netWorkManager.JoinLobby(NetWorkManager.MyActiveLobby.id, "player2", "blue");
        netWorkManager.StartGame();
      */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //Menu Buttons
        joinB = view.findViewById(R.id.joinB);
        createB = view.findViewById(R.id.createB);
        exitB = view.findViewById(R.id.ExitB);

        playB = view.findViewById(R.id.playB);

    }

    @Override
    public void onResume() {
        super.onResume();
        joinB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = getFragmentManager().beginTransaction();
                BrowserFragment bFrag = new BrowserFragment();
                fragmentTransaction.add(android.R.id.content, bFrag);
                fragmentTransaction.addToBackStack("bFrag");
                fragmentTransaction.commit();
            }
        });
        createB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = getFragmentManager().beginTransaction();
                CreateLobbyFragment cFrag = new CreateLobbyFragment();
                fragmentTransaction.add(android.R.id.content, cFrag);
                fragmentTransaction.addToBackStack("cFrag");
                fragmentTransaction.commit();
            }
        });
        exitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        playB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GameActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        joinB.setOnClickListener(null);
        exitB.setOnClickListener(null);
    }

    /*
    void readScore(){
        TextView tx = findViewById(R.id.scoreView);

        final String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int score = prefs.getInt("localScore", 0); //0 is the default value.
        tx.setText("high score: " +score);

        try{
            fileStream = openFileOutput("localScore.txt", Context.MODE_PRIVATE);

            InputStream is = getAssets().open("localScore.txt");
            int size = is.available();
            if(size != 0) {
                byte[] buffer = new byte[size];

                is.read(buffer);
                is.close();

                String bufferTransfer = new String(buffer);

                int currentBestScore = Integer.parseInt(bufferTransfer);

                tx.setText("Best score: " + currentBestScore);
            }else{
                tx.setText("No scores recorded");
            }


        }catch(Exception e){
            Log.e("error",e.getMessage());
        }
    }
*/
}