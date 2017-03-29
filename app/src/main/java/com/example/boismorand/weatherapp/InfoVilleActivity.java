package com.example.boismorand.weatherapp;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Latour on 17/03/2017.
 */

public class InfoVilleActivity extends Activity implements OnMapReadyCallback{
    private TextView name;
    private TextView country;
    private TextView coord;
    private TextView desc;
    private final static String API_KEY = "44d1e5b3dac1464fea563cc0fd9d8eb0";


    private Marker currentPositionMarker;
    private MapFragment map;
    private GoogleMap mGoogleMap;
    private boolean positionUpdated;
    private Location location;
    private City city;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos);



        map = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);

        city = getIntent().getParcelableExtra("city");
        name = (TextView) findViewById(R.id.name);
        country = (TextView) findViewById(R.id.country);
        coord = (TextView) findViewById(R.id.coord);
        location = new Location("location");
        location.setLongitude(city.getCoord().getLongitude());
        location.setLatitude(city.getCoord().getLatitude());
        desc = (TextView) findViewById(R.id.desc);

        name.setText( " Nom de ville : " + city.getName());
        country.setText("Pays : "+city.getCountry());
        double lat = city.getCoord().getLatitude();
        double lon = city.getCoord().getLongitude();
        coord.setText("Coordonnées :\n latitude = "+lat+", longitude = "+lon+" .");

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
                            //new DownloadImageTask((ImageView) findViewById(R.id.icon)).execute("http://openweathermap.org/img/w/" + meteo.getWeather()[0].getIcon() + ".png");
                            sunset.setTimeInMillis(base.getSys().getSunset() * 1000);
                            sunrise.setTimeInMillis(base.getSys().getSunrise() * 1000);
                            desc.setText("Meteo : "+ traduction(base.getWeather().get(0).getDescription()) + "\n"
                                    + "\nHumidité : " + base.getMain().getHumidity() + "%\n"
                                    + "\nPression : " + base.getMain().getPressure() + " hPa\n"
                                    + "\nTempérature : " + (base.getMain().getTemp() - 273.15) + " °C\n"
                                    + "\nVitesse de vent : " + base.getWind().getSpeed() + " m/s\n"
                                    + "\nDirection du vent : " + base.getWind().getDeg() + " (" + base.getWind().getDeg() + ") \n"
                                    + "\nLever soleil : " + sunrise.get(Calendar.HOUR_OF_DAY) + ":" + sunrise.get(Calendar.MINUTE)  + ":" + sunrise.get(Calendar.SECOND) + "\n"
                                    + "\nCoucher soleil : " + sunset.get(Calendar.HOUR_OF_DAY) + ":" + sunset.get(Calendar.MINUTE)  + ":" + sunset.get(Calendar.SECOND)  + "\n");
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

    public String traduction(String desc){
        if("clear sky".equals(desc.toLowerCase())){
            return "Le ciel est dégagé";
        }else if("few clouds".equals(desc.toLowerCase())){
            return "Il y a quelque nuages";
        }else if("overcast cloud".equals(desc.toLowerCase())){
            return "Le ciel est couvert";
        }else if("scattered clouds".equals(desc.toLowerCase())){
            return "Il y a des nuages dispersés";
        }else if("broken clouds".equals(desc.toLowerCase())){
            return "Il y a des nuages brisés";
        }else if("shower rain".equals(desc.toLowerCase())){
            return "Il pleut intensement";
        }else if("rain".equals(desc.toLowerCase())){
            return "Il pleut";
        }else if("thunderstorm".equals(desc.toLowerCase())){
            return "Il y a des orages";
        }else if("snow".equals(desc.toLowerCase())){
            return "Il neige";
        }else if("mist".equals(desc.toLowerCase())){
            return "Il y a du brouillard";
        }else{
            return desc;
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        mGoogleMap.getUiSettings().setScrollGesturesEnabled(false);
        mGoogleMap.getUiSettings().setRotateGesturesEnabled(false);
        mGoogleMap.getUiSettings().setTiltGesturesEnabled(false);

        LatLng initialLoc= mGoogleMap.getCameraPosition().target;
        LatLng coordinate = new LatLng(city.getCoord().getLatitude(),city.getCoord().getLongitude()); //Store these lat lng values somewhere. These should be constant.
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(coordinate, 8);
        mGoogleMap.animateCamera(location);

        mGoogleMap.addMarker(new MarkerOptions().position(coordinate).title("marker title"));
    }


}
