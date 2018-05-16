package com.example.brom.listviewjsonapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView aboutText = (TextView)findViewById(R.id.textView_about);

        aboutText.setText("HÄR STÅR DET SKIT OM APPEN, GRABBEN.");
    }
}
