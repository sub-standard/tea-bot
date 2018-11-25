package com.substandard.teabot;

import android.os.AsyncTask;
import android.util.Log;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

class TeaBot {
    static void run(int seconds){
        new RunBot().execute(seconds);
    }

    private static class RunBot extends AsyncTask<Integer, HttpResponse<JsonNode>, HttpResponse<JsonNode>> {
        protected HttpResponse<JsonNode> doInBackground(Integer... seconds) {
            try {
                return Unirest.post("http://192.168.43.24:3000/brew/" + seconds[0]).asJson();
            } catch (UnirestException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(HttpResponse<JsonNode> response) {
            Log.e("a", "b");
        }
    }
}
