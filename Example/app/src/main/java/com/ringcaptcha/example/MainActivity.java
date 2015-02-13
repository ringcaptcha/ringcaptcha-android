package com.ringcaptcha.example;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.thrivecom.ringcaptcha.RingcaptchaApplication;
import com.thrivecom.ringcaptcha.RingcaptchaApplicationHandler;
import com.thrivecom.ringcaptcha.RingcaptchaVerification;


public class MainActivity extends ActionBarActivity {

    private static final String API_KEY = "i3ufi2uqy6uzo6ili7yg";
    private static final String API_SECRET = "u8a2imy7aru2o9ozo8yf";
    private static final String TAG = "Ringcaptcha verification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RingcaptchaApplication.verifyPhoneNumber(getApplicationContext(), API_KEY, API_SECRET, new RingcaptchaApplicationHandler() {
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
    }

}
