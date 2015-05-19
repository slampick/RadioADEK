package com.example.radioadek.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.radioadek.R;
import com.example.radioadek.model.Radio;
import com.example.radioadek.util.MediaPlayerTask;

/**
 * Created by Monty on 19.05.2015.
 */
public class RadioFragment extends Fragment {

    private Radio radio;

    private MediaPlayerTask mTask;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_radio, container, false);

        TextView stationName = (TextView) view.findViewById(R.id.station_name_in_fragment);
        ImageView cover = (ImageView) view.findViewById(R.id.cover_station);
        TextView description = (TextView) view.findViewById(R.id.description_in_fragment);

        stationName.setText(radio.getStationName());
        description.setText(radio.getDescription());
        cover.setImageBitmap(radio.getCover());

        ImageButton playBtn = (ImageButton) view.findViewById(R.id.stop_play_btn);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTask != null) {
                    mTask.stop();
                    mTask = null;
                }
                else {
                    mTask = new MediaPlayerTask(getActivity().getApplicationContext(), radio.getUrlStream());
                    mTask.execute();
                }
            }
        });

        return view;
    }

    public void setRadio(Radio radio) {
        this.radio = radio;
    }
}
