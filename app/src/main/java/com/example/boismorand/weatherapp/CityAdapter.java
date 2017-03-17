package com.example.boismorand.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Latour on 14/03/2017.
 */

public class CityAdapter extends RecyclerView.Adapter<CityHolder>{

    public interface OnCityListener{
        void onCityClick(City city);
        void onCityLongClick(City city);
    }

    private static final String TAG = "CityAdapter";
    private ArrayList<City> items;
    private OnCityListener listener;

    public CityAdapter(ArrayList<City> items,OnCityListener listener){
        this.items = items;
        this.listener = listener;
    }

    @Override
    public CityHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_city,parent,false);
        return new CityHolder(view);
    }

    @Override
    public void onBindViewHolder(CityHolder holder, int position){
        City city = items.get(position);
        Log.i("coucou",city.toString());
        holder.bind(city,listener);
    }
    @Override
    public int getItemCount(){return items.size();}
}
