package com.mdworks.login;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

public class FairPage extends AppCompatActivity {
    private CardView getResult;
    public String mSource;
    public String mDestination;
    public String mSourceDistance;
    public String mDestinationDistance;
    public DatabaseAccess databaseAccess ;
    public Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fair_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CardView getResult = (CardView)findViewById(R.id.getResult);
        final Spinner source_list = findViewById(R.id.source_list);
        final Spinner destination_list = findViewById(R.id.destination_list);
        myDialog = new Dialog(this);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> station_list = new ArrayList<>();
        station_list = databaseAccess.getStation();
        station_list.add("Select Station");
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, station_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        source_list.setAdapter(adapter);
        source_list.setSelection(8);
        destination_list.setAdapter(adapter);
        destination_list.setSelection(8);

        source_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSource = source_list.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        destination_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDestination = destination_list.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void CalculateFair(View view) {
        if(mSource.equals("Select Station") || mDestination.equals("Select Station")){
            Toast.makeText(FairPage.this, "Please select stations", Toast.LENGTH_SHORT ).show();
        }else{
            //call some functions
            databaseAccess.open();
            mSourceDistance = databaseAccess.getDistance(mSource);
            mDestinationDistance = databaseAccess.getDistance(mDestination);
            databaseAccess.close();

            myDialog.setContentView(R.layout.list_layout);
            //getting Views
            Button closePopup = (Button) myDialog.findViewById(R.id.close_button);
            TextView show_fair = (TextView) myDialog.findViewById(R.id.show_fair);
            TextView station_count = (TextView) myDialog.findViewById(R.id.station_count);
            ListView station_list = (ListView) myDialog.findViewById(R.id.station_list);

            closePopup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                }
            });

            calculateFair cF= new calculateFair();
            //calculating values
            String fair = cF.getFair(mSourceDistance, mDestinationDistance);
            String stationCount = Integer.toString(cF.getStationCount(routList()));
            List<String> routeList = routList();
            //setView
            show_fair.setText(fair);
            station_count.setText(stationCount);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, routeList);
            station_list.setAdapter(arrayAdapter);

            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
            myDialog.show();

        }
    }

    public List<String> routList(){
        databaseAccess.open();
        List<String> rout_list = new ArrayList<>();
        rout_list = databaseAccess.getRoute(mSourceDistance, mDestinationDistance);
        databaseAccess.close();
        return rout_list;
    }

    public void setPopupView(){

    }

}
