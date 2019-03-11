package com.winhex.wys.wys.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.winhex.wys.wys.Presenter.Login.LoginImpl;
import com.winhex.wys.wys.Presenter.Start.StartPresenterImpl;
import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.SharedPreferencesUtil;
import com.winhex.wys.wys.Utils.ToastUtils;
import com.winhex.wys.wys.View.Istartview;
import com.winhex.wys.wys.bean.Startokenbean;

public class APPstar extends AppCompatActivity implements Istartview {

    Button login,zhuce;
    StartPresenterImpl startPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        startPresenter=new StartPresenterImpl(this);
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
                SharedPreferencesUtil.getInstance(APPstar.this,"tokens");
                String token=(String) SharedPreferencesUtil.getData("token","获取失败");
                startPresenter.startApp("http://192.168.0.8:8080/wys/",token);
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

    @Override
    public void getDataFailed(Throwable e) {
        e.getMessage();
    }

    @Override
    public void getStartSuccess(Startokenbean startokenbean) {
       if(startokenbean.isToken()){
           ToastUtils.show(APPstar.this,startokenbean.getMessage());
       }else {
           ToastUtils.show(APPstar.this,startokenbean.getMessage());
       }
    }
}

