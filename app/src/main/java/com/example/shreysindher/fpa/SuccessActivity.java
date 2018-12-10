package com.example.shreysindher.fpa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Success!! </font>"));

    }
}
