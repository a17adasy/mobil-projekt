package com.example.brom.listviewjsonapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;

public class MountainDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle info = getIntent().getExtras();
        String name = info.getString("INFO_NAME");
        String loc = info.getString("INFO_LOC");
        int height = info.getInt("INFO_HEIGHT");
        String img = info.getString("INFO_IMG");

        TextView nameText = (TextView)findViewById(R.id.textView);
        TextView locText = (TextView)findViewById(R.id.textView2);
        TextView heightText = (TextView)findViewById(R.id.textView3);
        ImageView image = (ImageView)findViewById(R.id.mountainImage);


        nameText.setText("Name: " + name);
        locText.setText("Location: " + loc);
        heightText.setText("Height: " + height);

        class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
            ImageView mountainImage;

            public DownloadImageTask(ImageView mountainImage) {
                this.mountainImage = mountainImage;
            }

            protected Bitmap doInBackground(String... urls) {
                String urldisplay = urls[0];
                Bitmap mountain = null;
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    mountain = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                return mountain;
            }

            protected void onPostExecute(Bitmap result) {
                mountainImage.setImageBitmap(result);
            }
        }
        new DownloadImageTask((ImageView)findViewById(R.id.mountainImage)).execute(img);
    }
}

