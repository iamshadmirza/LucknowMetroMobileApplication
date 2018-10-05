package com.mdworks.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class registration extends AppCompatActivity {

    private EditText username, email, password, phone;
    private CardView register;
    private TextView UserLogin;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIview();
        mAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.GONE);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    final String user = username.getText().toString().trim();
                    final String emailid = email.getText().toString().trim();
                    String pwd = password.getText().toString().trim();
                    final String phn = phone.getText().toString().trim();

                    mAuth.createUserWithEmailAndPassword(emailid, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.VISIBLE);
                            if(task.isSuccessful()){

                                Users users = new Users(user, emailid, phn);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(registration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(registration.this, MainActivity.class));
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(registration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                       }
                    });
                }
            }
        });

        UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registration.this, MainActivity.class));
            }
        });
    }

    private void setupUIview(){
        username = (EditText)findViewById(R.id.tvUserInput);
        email = (EditText)findViewById(R.id.tvUserEmail);
        password =  (EditText)findViewById(R.id.tvUserPwd);
        phone = (EditText)findViewById(R.id.tvUserPhone) ;
        register = (CardView)findViewById(R.id.registerButton);
        UserLogin = (TextView)findViewById(R.id.goToLogin);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }

    private Boolean validate(){
        Boolean result = false;
        String name= username.getText().toString();
        String emailid= email.getText().toString();
        String pwd= password.getText().toString();
        String phn = phone.getText().toString();

        if(name.isEmpty() || emailid.isEmpty() || pwd.isEmpty() || phn.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }
}











