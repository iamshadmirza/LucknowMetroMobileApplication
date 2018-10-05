package com.mdworks.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    private CardView fairbutton;
    private CardView mapbutton;
    private CardView cardbutton;
    private CardView contactbutton;
    private CardView lostandfoundbutton;
    private CardView donatebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUIview();
        fairbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Home.this, FairPage.class));
            }
        });

        fairbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Home.this, FairPage.class));
            }
        });

        mapbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Home.this, MapPage.class));
            }
        });

        cardbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Home.this, SmartCardPage.class));
            }
        });

        contactbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Home.this, ContactPage.class));
            }
        });

        lostandfoundbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Home.this, LostAndFoundPage.class));
            }
        });

        donatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Home.this, DonatePage.class));
            }
        });
    }

    private void setUIview(){
        fairbutton = (CardView)findViewById(R.id.fairbutton);
        mapbutton = (CardView)findViewById(R.id.mapbutton);
        cardbutton = (CardView)findViewById(R.id.cardbutton);
        contactbutton = (CardView)findViewById(R.id.contactbutton);
        lostandfoundbutton = (CardView)findViewById(R.id.lostandfoundbutton);
        donatebutton = (CardView)findViewById(R.id.donatebutton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.log_out) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Home.this, MainActivity.class));
            Toast.makeText(Home.this, "You have been Logged out", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
