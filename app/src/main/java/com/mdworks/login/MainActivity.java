package com.mdworks.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText emailid, userpwd;
    private CardView login;
    private TextView register;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUIview();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user!=null){
            Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show();
            startActivity( new Intent(MainActivity.this, Home.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String email = emailid.getText().toString().trim();
                    String password = userpwd.getText().toString().trim();
                    validatingLogin(email, password);

                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(MainActivity.this, registration.class));
            }
        });
    }

    //Main kaam yahan ho raha
    private void validatingLogin(String email, String password){

        if(email.equals("admin") && password.equals("1234567")){
          Toast.makeText(MainActivity.this, "This is Admin, you may proceed sir!", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "You are logged in", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MainActivity.this, Home.class));
                    }else{
                        Toast.makeText(MainActivity.this, "Login nahi hua babua ", Toast.LENGTH_LONG).show();
                    }
                }
            });
         }

    }

    // entries khali to nahi hai?
    private Boolean validate(){
        Boolean result = false;
        String e = emailid.getText().toString();
        String p =userpwd.getText().toString();
        if(e.isEmpty() || p.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }

    //housekeeping stuffs
    private void setUIview(){
        emailid = (EditText)findViewById(R.id.emailid);
        userpwd = (EditText)findViewById(R.id.userpwd);
        register = (TextView)findViewById(R.id.gotoRegister);
        login = (CardView)findViewById(R.id.login);
    }

    public void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();

    }
}
