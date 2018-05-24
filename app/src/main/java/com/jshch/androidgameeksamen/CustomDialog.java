package com.jshch.androidgameeksamen;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CustomDialog extends DialogFragment implements View.OnClickListener {

    public CreateLobbyFragment f;
    public Dialog d;
    public Button continueBtn, cancelBtn;
    public TextView playerName;
    private Spinner colorSpinner;

    public CustomDialog(CreateLobbyFragment f) {
        this.f = f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getActivity().setContentView(R.layout.player_details);
        continueBtn = getActivity().findViewById(R.id.continuebtn);
        cancelBtn = getActivity().findViewById(R.id.cancelbtn);
        continueBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        colorSpinner = getActivity().findViewById(R.id.color_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext().getApplicationContext(), R.array.colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);

        playerName = getActivity().findViewById(R.id.player_detail_name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continuebtn:
                f.CreateLobby(playerName.getText().toString(), colorSpinner.getSelectedItem().toString());
                break;
            case R.id.cancelbtn:
                dismiss();
                break;
            default:
                break;
        }
        colorSpinner = null;
        cancelBtn.setOnClickListener(null);
        continueBtn.setOnClickListener(null);
        dismiss();
    }
}
