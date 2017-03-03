package com.example.boismorand.weatherapp;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String s = getVilles();

        Gson gson = new Gson();
        ArrayList<City> city= new ArrayList<>();
        Type type = new TypeToken<ArrayList<City>>(){}.getType();
        city = gson.fromJson(s,type);
        Log.i("city",city.toString());

    }

    /**
     * return la liste de ville du fichier JSON
     * @return
     */
    public String getVilles(){
        String s = "";
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("city_france.json");
            if(inputStream != null)
            {
                BufferedReader br = null;
                StringBuilder sb = new StringBuilder();

                String line;
                try {

                    br = new BufferedReader(new InputStreamReader(inputStream));
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                s = sb.toString();
                Log.i("ville",s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }
}
