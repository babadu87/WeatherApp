package com.example.boismorand.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Latour on 14/03/2017.
 */

public class CityHolder extends RecyclerView.ViewHolder {

    private View view;
    private TextView nomView;
    private City city;

    public CityHolder(View itemView) {
        super(itemView);
        view = itemView;
        nomView = (TextView) view.findViewById(R.id.nom);
    }
    public void bind(City city, final CityAdapter.OnCityListener listener) {

        this.city = city;

        nomView.setText(this.city.getName());

        if(listener != null){
            view.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    listener.onCityClick(CityHolder.this.city);
                }
            });
/*
            view.setOnClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View view) {
                    listener.onCityLongClick(CityHolder.this.city);
                    return false;
                }
            });*/
        }
    }
}
