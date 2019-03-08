package com.winhex.wys.wys;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.sql.Time;

public class APPstar extends AppCompatActivity {

    Button login,zhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_appstar);
        View decorView = getWindow().getDecorView();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        Integer time=3000;
        login=findViewById(R.id.logding_login);
        zhuce=findViewById(R.id.loding_zhuce);
        login.setVisibility(View.INVISIBLE);
        zhuce.setVisibility(View.INVISIBLE);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent=new Intent(APPstar.this,MainActivity.class);
//                startActivity(intent);
//                APPstar.this.finish();

                login.setVisibility(View.VISIBLE);
                zhuce.setVisibility(View.VISIBLE);
            }
        },time);
    }
    public void register(View v){

    }
    public void logins(View v){
        Intent intent=new Intent(APPstar.this,Login.class);
        startActivity(intent);
        APPstar.this.finish();
    }
    }

