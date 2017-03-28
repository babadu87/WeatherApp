package com.example.boismorand.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

/**
 * Created by Latour on 28/03/2017.
 */

public class Autocompl extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocomplete);
        //final ArrayList<String> ListesVilles = getIntent().getParcelableExtra("Listevilles");

        Log.i("Autocompl", MainActivity.ListesVilles.toString());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, MainActivity.ListesVilles);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.autocomplete);
        textView.setAdapter(adapter);

    }
}
