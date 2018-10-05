package com.mdworks.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class DonatePage extends AppCompatActivity {
    private TextView insta;
    private TextView facebook;
    private TextView twitter;
    private TextView github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_page);
        insta = (TextView)findViewById(R.id.instalink);
        facebook = (TextView)findViewById(R.id.fblink);
        twitter = (TextView)findViewById(R.id.twitterlink);
        github = (TextView)findViewById(R.id.githublink);

        insta.setMovementMethod(LinkMovementMethod.getInstance());
        facebook.setMovementMethod(LinkMovementMethod.getInstance());
        twitter.setMovementMethod(LinkMovementMethod.getInstance());
        github.setMovementMethod(LinkMovementMethod.getInstance());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
