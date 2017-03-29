package com.example.boismorand.weatherapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private final static String API_KEY = "44d1e5b3dac1464fea563cc0fd9d8eb0";


    private CityAdapter adapter;


    //declaration des variable search view
    Button bouton;

    ListView listView;
    SearchView searchView;
    public final static ArrayList <String> ListesVilles = new ArrayList <> ();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String s = getVilles();
        bouton = (Button) findViewById(R.id.search);
        Gson gson = new Gson();
        ArrayList<City> cities= new ArrayList<>();
        Type type = new TypeToken<ArrayList<City>>(){}.getType();
        cities = gson.fromJson(s,type);
        Log.i("city",cities.toString());
        for (City city:cities) {
            ListesVilles.add(city.getName());
        }


        adapter = new CityAdapter(cities,new CityAdapter.OnCityListener(){
            //Ici voir pourquoi ça affiche une seule ville seulement

           @Override
            public void onCityClick(City city) {

                Intent intent = new Intent(MainActivity.this, InfoVilleActivity.class);
                intent.putExtra("city",city);
                startActivity(intent);
                //Toast.makeText(MainActivity.this,city.getName(),Toast.LENGTH_LONG).show();
            }


            @Override
            public void onCityLongClick(City city) {
                // Ici mettre l'action quand on appuie longtemps sur l'item
            }
        });
        //on chope le recycler view dans l'activity
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(adapter);

        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Autocompl.class);
                startActivity(intent);
            }
        });
        // search view

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
