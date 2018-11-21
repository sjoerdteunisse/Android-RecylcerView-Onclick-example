package com.axr.sjoerd.ApplicationLogic;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.axr.sjoerd.contactcard.DomainLayer.Person;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class RandomPersonApi {

    private static final String API_URI = "https://randomuser.me/api/";


    public ArrayList<Person> jsonObject() {
        final ArrayList<Person> personCollection = new ArrayList<Person>();

        for (int i = 0; i < 10; i++) {
            String json = getJSON();

            try {
                JSONObject rawObj = new JSONObject(json);
                String firstName = rawObj.getJSONArray("results").getJSONObject(0).getJSONObject("name").getString("first");
                String lastName = rawObj.getJSONArray("results").getJSONObject(0).getJSONObject("name").getString("last");
                String title = rawObj.getJSONArray("results").getJSONObject(0).getJSONObject("name").getString("title");
                String gender = rawObj.getJSONArray("results").getJSONObject(0).getString("gender");
                String email = rawObj.getJSONArray("results").getJSONObject(0).getString("email");
                String city = rawObj.getJSONArray("results").getJSONObject(0).getJSONObject("location").getString("city");
                String thumbnail = rawObj.getJSONArray("results").getJSONObject(0).getJSONObject("picture").getString("large");

                personCollection.add(new Person(firstName, lastName, title, gender, email, city, thumbnail));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return personCollection;
    }

    public static String getJSON() {

        final String[] result = {""};

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection con = null;
                try {
                    URL u = new URL(API_URI);
                    con = (HttpsURLConnection) u.openConnection();

                    con.connect();

                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    result[0] = sb.toString();

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (con != null) {
                        try {
                            con.disconnect();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }

        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0];
    }


    private class demoFromLesson extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }

}

