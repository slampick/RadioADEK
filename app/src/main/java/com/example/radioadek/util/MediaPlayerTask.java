package com.example.radioadek.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.IOException;

public class MediaPlayerTask extends AsyncTask {

    private static MediaPlayer player;
    private String url;
    private Context context;

    public MediaPlayerTask(Context ctx, String url) {
        this.url = url;
        this.context = ctx;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        stop();
        player = new MediaPlayer();
        try {
            player.setDataSource(context, Uri.parse(url));
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void pause() {
        if(player != null && player.isPlaying()) {
            player.pause();
        }
    }

    public void stop() {
        if(player != null && player.isPlaying()) {
            player.stop();
            player.release();
            player = null;
        }
    }

    public void resume() {
        if(player != null) {
            player.start();
        }
    }
}