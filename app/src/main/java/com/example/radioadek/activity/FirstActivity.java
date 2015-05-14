package com.example.radioadek.activity;

import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.radioadek.R;
import com.example.radioadek.adapter.RadioAdapter;
import com.example.radioadek.model.Radio;
import com.example.radioadek.util.MediaPlayerTask;

import java.util.ArrayList;


public class FirstActivity extends ActionBarActivity {

    private static MediaPlayerTask mpTask;
    private static int currentPosition = Integer.MAX_VALUE;
    private static RadioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        ListView list = (ListView) findViewById(R.id.radio_list);

        ArrayList<Radio> radios = new ArrayList<>();

        radios.add(new Radio("Radio Record", "Station, Deep, Breaks, Dancecore, Dubstep, Trap," +
                "Hardstyle, Rock",
                BitmapFactory.decodeResource(getResources(), R.drawable.record),
                "http://online.radiorecord.ru:8101/rr_128"));
        radios.add(new Radio("Biker-FM", "Rock",
                BitmapFactory.decodeResource(getResources(), R.drawable.biker_fm),
                "http://stream1.radiostyle.ru:8001/biker-fm"));
        radios.add(new Radio("SKY.FM Roots Reggae", "Регги",
                BitmapFactory.decodeResource(getResources(), R.drawable.sky_fm),
                "http://radio02-cn03.akadostream.ru:8104/reggae_1_sky96.mp3"));
        radios.add(new Radio("Bluese", "Bluese",
                BitmapFactory.decodeResource(getResources(), R.drawable.bluese),
                "http://music.myradio.com.ua:8000/blues128.mp3"));
        radios.add(new Radio("Dance Club", "Dance, Electrinic, HIP-HOP",
                BitmapFactory.decodeResource(getResources(), R.drawable.dance_club),
                "http://music.myradio.com.ua:8000/dance128.mp3"));
        radios.add(new Radio("Дорожное радио", "POP",
                BitmapFactory.decodeResource(getResources(), R.drawable.street_radio),
                "http://dorognoe.hostingradio.ru:8000/dorognoe"));
        radios.add(new Radio("Юмор FM", "POP",
                BitmapFactory.decodeResource(getResources(), R.drawable.humor),
                "http://89.20.132.26:8000/v5_1"));
        radios.add(new Radio("Европа ПЛЮС", "Station, Breaks, Deep, Dance Core, Dubstep, Trap, Hard",
                BitmapFactory.decodeResource(getResources(), R.drawable.evrope_plus),
                "http://onair.eltel.net/europaplus-128k"));
        radios.add(new Radio("Zaycev FM", "Pop, Disco, Club, RnB",
                BitmapFactory.decodeResource(getResources(), R.drawable.zaycev),
                "http://zaycev.fm:9002/zaycev(128)"));
        radios.add(new Radio("Шансон FM", "Шансон",
                BitmapFactory.decodeResource(getResources(), R.drawable.shanson),
                "http://music.myradio.com.ua:8000/shanson128.mp3"));

        adapter = new RadioAdapter(this.getApplicationContext(), radios);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mpTask != null && currentPosition == position) {
                    mpTask.stop();
                }
                else {
                    Radio radio = (Radio) adapter.getItem(position);
                    mpTask = new MediaPlayerTask(getApplicationContext(), radio.getUrlStream());
                    mpTask.execute();
                }
                currentPosition = position;

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
