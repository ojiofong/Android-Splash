package com.ojiofong.splash.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ojiofong.splash.R;
import com.ojiofong.splash.Util;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickLogin(View view){
        Util.setUserLoginStatus(true, this);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
