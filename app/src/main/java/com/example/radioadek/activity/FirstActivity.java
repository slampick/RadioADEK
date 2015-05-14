package com.example.radioadek.activity;

import android.content.res.Resources;
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
        for(int i = 0; i < 5; i++) {
            Radio radio = new Radio("Radio Record", "Trance, Electronic",
                    BitmapFactory.decodeResource(getResources(), R.drawable.record),
                    "http://cast.loungefm.com.ua/terrace128");
            radios.add(radio);
        }

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
