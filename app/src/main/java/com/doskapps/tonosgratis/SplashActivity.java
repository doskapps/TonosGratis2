package com.doskapps.tonosgratis;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.doskapps.utils.Constant;
import com.doskapps.utils.JsonUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }

        try {
           Constant.isFromPush = getIntent().getExtras().getBoolean("ispushnoti", false);
        } catch (Exception e) {
            Constant.isFromPush = false;
        }
        try {
            Constant.isFromNoti = getIntent().getExtras().getBoolean("isnoti", false);
        } catch (Exception e) {
            Constant.isFromNoti = false;
        }

        JsonUtils jsonUtils = new JsonUtils(SplashActivity.this);
        jsonUtils.setStatusColor(getWindow());

        if(!Constant.isFromNoti) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    openMainActivity();
                }
            }, 1000);
        } else {
            openMainActivity();
        }
    }

    private void openMainActivity() {
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}