package com.example.boismorand.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by boismorand on 21/03/2017.
 */

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    List list = null;
    private ArrayList<String> listesVilles;
    private LayoutInflater inflater;
    // Déclaration des variables

   public ListViewAdapter(Context contexte, List<String> liste) {
       mContext = contexte;
       list = liste;
       inflater = LayoutInflater.from(mContext);
       listesVilles = new ArrayList<>();
       this.listesVilles.addAll (liste);
    }

    public class ViewHolder {
        TextView name;
    }

    public int getCount () {
        return list.size ();
    }

    public String getItem (int position ) {
        return list.get(position).toString();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(listesVilles.get(position));
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0) {
            list.addAll(listesVilles);
        } else {
            for (String wp : listesVilles) {
                if (wp.toLowerCase(Locale.getDefault()).contains(charText)) {
                    list.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


}