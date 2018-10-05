package com.mdworks.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class ContactPage extends AppCompatActivity {
    private TextView lmrcmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);
        lmrcmail = (TextView)findViewById(R.id.lmrcmail);
        lmrcmail.setMovementMethod(LinkMovementMethod.getInstance());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
