package com.ojiofong.splash.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.ojiofong.splash.Util;

import java.lang.ref.WeakReference;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Splash background is available as the activity theme background
        // before layout inflation happens making it optimized against cold startup

       if (savedInstanceState == null){
           int SPLASH_TIME = 3000; // 1 second
           new SplashHandler(this).sendEmptyMessageDelayed(0, SPLASH_TIME);
       }

    }


    private void goToMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    private void goToLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
    }

    private static class SplashHandler extends Handler {

        private static WeakReference<SplashActivity> mWeakReference;

        SplashHandler(SplashActivity mActivity) {
            mWeakReference = new WeakReference<>(mActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SplashActivity mActivityContext = mWeakReference.get();
            if (mActivityContext != null) {
                if (Util.isUserLoggedIn(mActivityContext)){
                    mActivityContext.goToMainScreen();
                }else {
                    mActivityContext.goToLoginScreen();
                }
            }
        }
    }
}
