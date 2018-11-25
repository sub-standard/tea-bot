package com.substandard.teabot;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

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
                try {
                    /*URL url = new URL("http://192.168.43.24:3000/brew/"+sec);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    DataOutputStream a = new DataOutputStream(new DataOutputStream((con.getOutputStream())));
                    a.writeBytes("http://192.168.43.24:3000/brew/"+sec);

                    con.disconnect();*/


                    /*HttpClient httpclient = HttpClients.createDefault();
                    HttpPost httppost = new HttpPost("http://192.168.43.24:3000/brew/"+sec);
                    httpclient.execute(httppost);*/
                    Unirest.post("http://192.168.43.24:3000/brew/"+sec);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Connection failed"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    break;
                }
                Toast.makeText(getApplicationContext(),"It will be brewed for "+ sec +" seconds",Toast.LENGTH_SHORT).show();

                break;
        }
    }

}
