package com.substandard.teabot;

import android.os.AsyncTask;
import android.widget.Toast;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONException;

class FaceAnalysis {
    static void analyse(byte[] picture) {
        new FaceDetectApi().execute(picture);
    }

    private static class FaceDetectApi extends AsyncTask<byte[], HttpResponse<JsonNode>, HttpResponse<JsonNode>> {
        protected HttpResponse<JsonNode> doInBackground(byte[]... picture) {
            try {
                return Unirest.post("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect")
                        .header("Ocp-Apim-Subscription-Key", "7610f1fd8d744d16a1f46cf4c2ef35d1")
                        .header("Content-Type", "application/octet-stream")
                        .queryString("returnFaceId", "false")
                        .queryString("returnFaceLandmarks", "false")
                        .queryString("returnFaceAttributes", "smile")
                        .body(picture[0])
                        .asJson();
            } catch (UnirestException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(HttpResponse<JsonNode> response) {
            try {
                if (response.getStatus() == 200) {
                    JSONArray body = response.getBody().getArray();

                    double smile = body.getJSONObject(0).getJSONObject("faceAttributes").getDouble("smile");
                    evaulateEmotion(smile);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static void evaulateEmotion(double smile) {
        if (smile > 0.5) {
            TeaBot.run(60);
        }
    }
}