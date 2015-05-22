package com.example.radioadek.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.radioadek.R;
import com.example.radioadek.model.Radio;
import com.example.radioadek.util.MediaPlayerTask;

import java.util.ArrayList;

/**
 * Created by Monty on 19.05.2015.
 */
public class RadioFragment extends Fragment {


    private int currentRadioPosition;
    ArrayList<Radio> radios;

    private View view;

    private MediaPlayerTask mTask;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_radio, container, false);
        this.view = view;

        TextView stationName = (TextView) view.findViewById(R.id.station_name_in_fragment);
        ImageView cover = (ImageView) view.findViewById(R.id.cover_station);
        TextView description = (TextView) view.findViewById(R.id.description_in_fragment);

        stationName.setText(radios.get(currentRadioPosition).getStationName());
        description.setText(radios.get(currentRadioPosition).getDescription());
        cover.setImageBitmap(radios.get(currentRadioPosition).getCover());

        ImageButton playBtn = (ImageButton) view.findViewById(R.id.stop_play_btn);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlayCurrentRadio();
            }
        });

        ImageButton leftBtn = (ImageButton) view.findViewById(R.id.left_btn);
        ImageButton rightLft = (ImageButton) view.findViewById(R.id.right_btn);

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRadioPosition--;
                startPlayCurrentRadio();
            }
        });

        rightLft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRadioPosition++;
                startPlayCurrentRadio();
            }
        });

        return view;
    }

    public void startPlayCurrentRadio() {
        if(mTask != null) {
            mTask.stop();
            mTask = null;
        }
        else {
            mTask = new MediaPlayerTask(getActivity().getApplicationContext(),
                    radios.get(currentRadioPosition).getUrlStream());
            mTask.execute();
            TextView stationName = (TextView) view.findViewById(R.id.station_name_in_fragment);
            ImageView cover = (ImageView) view.findViewById(R.id.cover_station);
            TextView description = (TextView) view.findViewById(R.id.description_in_fragment);

            stationName.setText(radios.get(currentRadioPosition).getStationName());
            description.setText(radios.get(currentRadioPosition).getDescription());
            cover.setImageBitmap(radios.get(currentRadioPosition).getCover());
        }
    }

    public void setRadiosWithPosition(ArrayList<Radio> radios, int position) {
        this.radios = radios;
        this.currentRadioPosition = position;
    }
}
