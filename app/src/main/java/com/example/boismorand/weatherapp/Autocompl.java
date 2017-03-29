package com.example.boismorand.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

import static com.example.boismorand.weatherapp.R.id.autocomplete;

/**
 * Created by Latour on 28/03/2017.
 */

public class Autocompl extends Activity{

    private City city;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocomplete);
        //final ArrayList<String> ListesVilles = getIntent().getParcelableExtra("Listevilles");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, MainActivity.ListesVilles);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(autocomplete);
        textView.setAdapter(adapter);

    }


    // fonction qui sert a appeler une nouvelle vue sur le click du bouton
    public void afficherInfoVille(View view){
        Intent intent = new Intent(Autocompl.this, InfoVilleActivity.class);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autocomplete);
        Log.i("test",autoCompleteTextView.getText().toString());
        for(City cityTemp : MainActivity.cities){
            Log.i("test",cityTemp.toString());
            if(cityTemp.getName().equals(autoCompleteTextView.toString())){
                city = cityTemp;
            }
        }
        Log.i("city",city.toString());
        if(city!=null){
            intent.putExtra("city",city);
            startActivity(intent);
        }

    }
}
