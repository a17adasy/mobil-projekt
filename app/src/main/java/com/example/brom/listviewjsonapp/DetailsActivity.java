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

public class DetailsActivity extends AppCompatActivity {

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
        String comp = info.getString("INFO_COMP");

        TextView nameText = (TextView)findViewById(R.id.textView);
        TextView locText = (TextView)findViewById(R.id.textView2);
        TextView compText = (TextView)findViewById(R.id.textView3);


        nameText.setText("Name: " + name);
        locText.setText("Location: " + loc);
        compText.setText("Company: " + comp);

    }
}

