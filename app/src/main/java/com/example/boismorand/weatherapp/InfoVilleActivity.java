package com.example.boismorand.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Calendar;

/**
 * Created by Latour on 17/03/2017.
 */

public class InfoVilleActivity extends Activity {
    private TextView name;
    private TextView country;
    private TextView coord;
    private final static String API_KEY = "44d1e5b3dac1464fea563cc0fd9d8eb0";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);
        final City city = getIntent().getParcelableExtra("city");
        name = (TextView) findViewById(R.id.name);
        country = (TextView) findViewById(R.id.country);
        coord = (TextView) findViewById(R.id.coord);

        name.setText(city.getName());
        country.setText(city.getCountry());
        coord.setText(city.getCoord().toString());

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url ="http://api.openweathermap.org/data/2.5/weather?id="+city.getId()+"&appid="+API_KEY;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try{
                            Gson gson = new Gson();
                            Base base = gson.fromJson(response,Base.class);
                            Calendar sunset = Calendar.getInstance();
                            Calendar sunrise = Calendar.getInstance();
                            new DownloadImageTask((ImageView) findViewById(R.id.icon)).execute("http://openweathermap.org/img/w/" + meteo.getWeather()[0].getIcon() + ".png");
                            sunset.setTimeInMillis(base.getSys().getSunset() * 1000);
                            sunrise.setTimeInMillis(base.getSys().getSunrise() * 1000);
                            name.setText("Meteo desc: "+ base.getWeather().get(0).getDescription() + "\n"
                                    + "Humidity : " + base.getMain().getHumidity() + "%\n"
                                    + "Pressure : " + base.getMain().getPressure() + " hPa\n"
                                    + "Temp : " + (base.getMain().getTemp() - 273.15) + " °C\n"
                                    + "Win Speed : " + base.getWind().getSpeed() + " m/s\n"
                                    + "Win Dir : " + base.getWind().getDeg() + " (" + base.getWind().getDeg() + ") \n"
                                    + "Lever soleil : " + sunrise.get(Calendar.HOUR_OF_DAY) + ":" + sunrise.get(Calendar.MINUTE)  + ":" + sunrise.get(Calendar.SECOND) + "\n"
                                    + "Coucher soleil : " + sunset.get(Calendar.HOUR_OF_DAY) + ":" + sunset.get(Calendar.MINUTE)  + ":" + sunset.get(Calendar.SECOND)  + "\n");
                        }catch (Exception error){name.setText(response + "\n" + error.getMessage());}
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InfoVilleActivity.this, "Erreur", Toast.LENGTH_LONG).show();
            }
        });
                queue.add(stringRequest);

    }
}
