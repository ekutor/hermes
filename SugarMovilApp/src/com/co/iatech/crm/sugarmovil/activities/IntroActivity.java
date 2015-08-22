package com.co.iatech.crm.sugarmovil.activities;

import com.co.iatech.crm.sugarmovil.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class IntroActivity extends Activity {

    /**
     * The Splash screen Timeout.
     */
    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intentLogin = new Intent(IntroActivity.this,
                        LoginActivity.class);
                startActivity(intentLogin);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
