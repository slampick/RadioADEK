package com.example.radioadek.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.radioadek.R;
import com.example.radioadek.model.Radio;

import java.util.ArrayList;

/**
 * Created by Monty on 14.05.2015.
 */
public class RadioAdapter extends BaseAdapter {

    private ArrayList<Radio> radios;
    private LayoutInflater inflater;
    private Context context;

    public RadioAdapter(Context context, ArrayList<Radio> radios) {
        this.radios = radios;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return radios.size();
    }

    @Override
    public Object getItem(int position) {
        return radios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = inflater.inflate(R.layout.radio_list_item, parent, false);
        }
        ImageView cover = (ImageView) view.findViewById(R.id.radio_image);
        TextView stationName = (TextView) view.findViewById(R.id.station_name);
        TextView description = (TextView) view.findViewById(R.id.content_description);

        Radio radio = (Radio) getItem(position);
        cover.setImageBitmap(radio.getCover());
        stationName.setText(radio.getStationName());
        description.setText(radio.getDescription());

        return view;
    }
}
