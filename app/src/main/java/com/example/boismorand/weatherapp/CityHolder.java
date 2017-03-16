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
    public void bind(final City city, final CityAdapter.OnCityListener listener) {

        this.city = city;

        nomView.setText(city.getName());

        if(listener != null){
            view.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    listener.onCityClick(city);
                }
            });

            /*view.setOnClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View view) {
                    listener.onCityLongClick(city);
                    return false;
                }
            });*/
        }
    }
}
