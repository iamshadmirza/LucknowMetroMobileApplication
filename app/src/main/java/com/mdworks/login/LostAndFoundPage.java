package com.mdworks.login;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LostAndFoundPage extends AppCompatActivity {
    private EditText yourname;
    private EditText mobile;
    private EditText lastseen;
    private EditText details;
    private CardView submitlaf;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Lost and Found info");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found_page);
        setUIview();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        submitlaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    final String user = yourname.getText().toString().trim();
                    final String contact = mobile.getText().toString().trim();
                    final String location = lastseen.getText().toString().trim();
                    final String detail = details.getText().toString().trim();
                    lafInfo lafObject = new lafInfo(user, contact, location, detail);
                    myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(lafObject).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(LostAndFoundPage.this, "Information submitted", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(LostAndFoundPage.this, "Please try again", Toast.LENGTH_SHORT).show();
                            }}
                    });

                }
            }
        });
    }

    private void setUIview(){
        yourname = (EditText)findViewById(R.id.yourname);
        mobile = (EditText)findViewById(R.id.mobile);
        lastseen = (EditText)findViewById(R.id.lastseen);
        details = (EditText)findViewById(R.id.details);
        submitlaf = (CardView)findViewById(R.id.submitlaf);
    }

    private Boolean validate(){
        Boolean result = false;
        String name= yourname.getText().toString();
        String contact= mobile.getText().toString();
        String location= lastseen.getText().toString();
        String detail = details.getText().toString();

        if(name.isEmpty() || contact.isEmpty() || location.isEmpty() || detail.isEmpty()){
            Toast.makeText(this, "Please enter all the details and submit", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }
}
