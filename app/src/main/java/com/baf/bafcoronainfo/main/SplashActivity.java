package com.baf.bafcoronainfo.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.view.Window;


import com.baf.bafcoronainfo.R;
import com.baf.bafcoronainfo.util.ToastUtil;


import java.util.Timer;
import java.util.TimerTask;



public class SplashActivity extends Activity {
    private String TAG = SplashActivity.class.getSimpleName();
    private Context context;
    private ToastUtil toastUtil;
    private TimerTask sostt;
    private final long period = 3000;
    private final int delay = 3000;
    private Timer sostimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        context = this;

        startTimer();



    }

    void startTimer() {
        try {
            sostimer = new Timer();
            sostt = new TimerTask() {

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            stopTimer();
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            SplashActivity.this.finish();

                        }
                    });

                }
            };
            sostimer.schedule(sostt, delay, period);

        } catch (final Exception e) {
        }

    }  void stopTimer() {
        try {
            if (sostimer != null) {
                sostimer.cancel();
                sostimer = null;
            }
            if (sostt != null) {
                sostt.cancel();
                sostt = null;
            }
        } catch (final Exception e) {
        }
    }

    protected void onResume() {

        super.onResume();
        overridePendingTransition(0, 0);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



}
