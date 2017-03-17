package com.example.boismorand.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Latour on 17/03/2017.
 */

public class InfoVilleActivity extends Activity {
    private TextView name;
    private TextView country;
    private TextView coord;

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


    }
}
