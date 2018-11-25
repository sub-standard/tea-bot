package com.substandard.teabot;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetTime extends AppCompatActivity implements View.OnClickListener{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_set_time);

        Button startButton = this.findViewById(R.id.btn_begin);
        startButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        //do what you want to do when button is clicked
        switch (v.getId()) {
            case R.id.btn_begin:
                EditText edit = findViewById(R.id.timeToBrew);
                String str = edit.getText().toString();
                int sec = 0;
                try {
                    int seconds = Integer.parseInt(str);
                    sec = seconds;
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"This is not valid format",Toast.LENGTH_SHORT).show();
                    break;
                }

                FaceAnalysis.callServer(sec);

                Toast.makeText(getApplicationContext(),"It will be brewed for "+ sec +" seconds",Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
