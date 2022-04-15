package com.ringcaptcha.example;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.thrivecom.ringcaptcha.RingcaptchaAPIHandler;
import com.thrivecom.ringcaptcha.RingcaptchaApplication;
import com.thrivecom.ringcaptcha.RingcaptchaApplicationHandler;
import com.thrivecom.ringcaptcha.RingcaptchaVerification;


public class MainActivity extends AppCompatActivity {

    private static final String APP_KEY = "REPLACE_WITH_YOUR_APP_KEY";
    private static final String API_SECRET = "REPLACE_WITH_YOUR_SECREY_KEY";
    private static final String TAG = "Ringcaptcha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RingcaptchaApplication.onboard(getApplicationContext(), APP_KEY, API_SECRET, new RingcaptchaApplicationHandler() {
                    @Override
                    public void onSuccess(RingcaptchaVerification rcObj) {
                        Log.i(TAG, "success");
                    }

                    @Override
                    public void onCancel() {
                        Log.i(TAG, "cancel");
                    }
                });
            }
        });


        Uri data = getIntent().getData();
        if(data != null && data.getQueryParameter("pin") != null) {
            String pin = data.getQueryParameter("pin");

            RingcaptchaApplication.onboard(getApplicationContext(), APP_KEY, API_SECRET, pin, new RingcaptchaApplicationHandler() {

                @Override
                public void onSuccess(RingcaptchaVerification ringObj) {
                    Log.i(TAG, "success");
                }

                @Override
                public void onCancel() {
                    Log.i(TAG, "cancel");
                }
            });
        }
    }

}
