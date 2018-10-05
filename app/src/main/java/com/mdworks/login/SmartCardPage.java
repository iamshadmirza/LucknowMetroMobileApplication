package com.mdworks.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SmartCardPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_card_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
