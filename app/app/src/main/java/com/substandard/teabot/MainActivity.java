package com.substandard.teabot;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startTea(View view) {
        try {
            Unirest.post("http://test.com")
                    .header("accept", "application/json")
                    .field("time", "60")
                    .asJson();
        } catch (UnirestException e) {
            System.out.println("Error");
        }
    }
}
