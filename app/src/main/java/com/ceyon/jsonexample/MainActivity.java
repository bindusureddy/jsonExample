package com.ceyon.jsonexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

//Array list of name,phonenumber,email;
        ArrayList<String> id = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> country = new ArrayList<>();
        ArrayList<String> city = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

// set Linearlayout with default vertical Layout
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);

            try {
                // get JSONObject from JSON file
                JSONObject obj = new JSONObject(loadJSONFromAsset());
                // fetch JSONArray named users
                JSONArray userArray = obj.getJSONArray("data");
                // implement for loop for getting users list data
                for (int i = 0; i < userArray.length(); i++) {
                    // create a JSONObject for fetching single user data
                    JSONObject userDetail = userArray.getJSONObject(i);
                    // fetch email and name and store it in arraylist
                    id.add(userDetail.getString("id"));
                    name.add(userDetail.getString("name"));
                    country.add(userDetail.getString("country"));
                    city.add(userDetail.getString("city"));
                    // create a object for getting contact data from JSONObject
                    // fetch mobile number and store it in arraylist

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //  call the constructor of CustomAdapter to send the reference and data to Adapter
            CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, id, name, country,city);
            recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
        }

        public String loadJSONFromAsset() {
            String json = null;
            try {
                InputStream is = getAssets().open("data.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            return json;
        }
    }
