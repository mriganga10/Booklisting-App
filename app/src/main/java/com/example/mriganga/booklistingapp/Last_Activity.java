package com.example.mriganga.booklistingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Last_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_);
        TextView t =(TextView)findViewById(R.id.link);
        t.setText(getIntent().getStringExtra("link"));
    }
}
