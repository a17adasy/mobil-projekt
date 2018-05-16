package com.example.brom.listviewjsonapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dressing_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle info = getIntent().getExtras();
        String name = info.getString("INFO_NAME");
        String loc = info.getString("INFO_LOC");
        String comp = info.getString("INFO_COMP");
        int size = info.getInt("INFO_SIZE");
        int cost = info.getInt("INFO_COST");
        String auxdata = info.getString("INFO_AUX");

        TextView nameText = (TextView)findViewById(R.id.textView);
        TextView sizeText = (TextView)findViewById(R.id.textView2);
        TextView costText = (TextView)findViewById(R.id.textView3);
        TextView locText = (TextView)findViewById(R.id.textView4);
        TextView compText = (TextView)findViewById(R.id.textView5);
        TextView auxText = (TextView)findViewById(R.id.textView6);


        nameText.setText(name);
        sizeText.setText("Size: " + size + "ml");
        costText.setText("Cost: " + cost + "kr");
        locText.setText("Location: " + loc);
        compText.setText("Company: " + comp);
        auxText.setText("Review: " + auxdata);

    }
}

