package com.mdworks.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MapPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
