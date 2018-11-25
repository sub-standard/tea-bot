package com.substandard.teabot;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Home extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        Button upButton = view.findViewById(R.id.start_button);
        upButton.setOnClickListener(this);
       return view;
    }

    public void onClick(View v) {
        //do what you want to do when button is clicked
        switch (v.getId()) {
            case R.id.start_button:
                Intent in = new Intent(getActivity(), SetTime.class);
                in.putExtra("Some", "Some");
                startActivity(in);
                break;
        }
    }
}