package com.example.shreysindher.fpa;

import android.app.KeyguardManager;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int LOCK_REQUEST_CODE = 221;
    private static final int SECURITY_SETTING_REQUEST_CODE = 233;
    Button login;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Fingerprint Authentication </font>"));

        tv = findViewById(R.id.tView);
        login = findViewById(R.id.LoginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateApp();
            }
        });
    }

    private void authenticateApp() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        Intent i = keyguardManager.createConfirmDeviceCredentialIntent("Unlock app to get success",
                "Confirm your screen lock PIN,Pattern or Password");
        try {
            startActivityForResult(i, LOCK_REQUEST_CODE);
        } catch (Exception e) {
            Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
            try {
                startActivityForResult(intent, SECURITY_SETTING_REQUEST_CODE);
            } catch (Exception ex) {
                tv.setText("Please set the screen lock Manually by navigating to : Settings>Security");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOCK_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Intent successIntent = new Intent(MainActivity.this, SuccessActivity.class);
                    startActivity(successIntent);
                } else {
                    tv.setText("Failed to get Success :(\nTry Again!");
                }
            /*case SECURITY_SETTING_REQUEST_CODE:
                if(isDeviceSecure())
                {
                    Toast.makeText(this, "Device is Secure", Toast.LENGTH_SHORT).show();
                    authenticateApp();
                }
                else
                {
                    tv.setText("Device is feeling insecure \nOR\nYou cancelled the request");
                }
        }*/
        }

    /*private boolean isDeviceSecure()
    {
        KeyguardManager keyguardManager = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);
        return keyguardManager.isDeviceSecure();
    }*/
    }
}
