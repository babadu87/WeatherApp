package com.example.boismorand.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Latour on 17/03/2017.
 */

public class InfoVilleActivity extends Activity {
    private TextView name;
    private TextView country;
    private TextView coord;
    //final TextView mTextView = (TextView) findViewById(R.id.text);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);
        City city = getIntent().getParcelableExtra("city");
        name = (TextView) findViewById(R.id.name);
        country = (TextView) findViewById(R.id.country);
        coord = (TextView) findViewById(R.id.coord);

        name.setText(city.getName());
        country.setText(city.getCountry());
        coord.setText(city.getCoord().toString());
/*
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";

        // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                Log.i("repserveur",response.substring(0,500));
                                //mTextView.setText("Response is: "+ response.substring(0,500));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work!");
                    }
                });
        // Add the request to the RequestQueue.
                queue.add(stringRequest);
                */
    }
}
