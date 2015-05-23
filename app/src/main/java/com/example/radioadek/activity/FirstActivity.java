package com.example.radioadek.activity;

import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.radioadek.R;
import com.example.radioadek.adapter.RadioAdapter;
import com.example.radioadek.fragment.RadioFragment;
import com.example.radioadek.model.Radio;
import com.example.radioadek.util.MediaPlayerTask;

import java.util.ArrayList;


public class FirstActivity extends ActionBarActivity {

    private LinearLayout rootLeft;
    private ArrayList<Radio> radios;
    private ListView radioList;

    private int currentDrawerPosition = Integer.MAX_VALUE;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio_drawer_layout);

        rootLeft = (LinearLayout) findViewById(R.id.root_left);
        drawerLayout = (DrawerLayout) findViewById(R.id.root_drawer);

        radios = new ArrayList<>();
        fillRadio(radios);

        radioList = (ListView) findViewById(R.id.radio_list);
        final RadioAdapter adapter = new RadioAdapter(getApplicationContext(), radios);
        radioList.setAdapter(adapter);

        radioList.setOnItemClickListener(new DrawerClickListener());

        selectItem(0);
    }

    private void fillRadio(ArrayList<Radio> radios) {
        radios.add(new Radio("Radio Record", "Station, Deep, Breaks, Dancecore, Dubstep, Trap",
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

    private class DrawerClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        if(position == currentDrawerPosition) {
            drawerLayout.closeDrawer(rootLeft);
        }
        else {
            RadioFragment fragment = new RadioFragment();
            fragment.setRadiosWithPosition(radios, position);
            currentDrawerPosition = position;

            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.container, fragment).commit();

            drawerLayout.closeDrawer(rootLeft);
        }
    }
}
