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
import com.winhex.wys.wys.Utils.UrlIPconfig;
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
                if(token=="获取失败"){
                    ToastUtils.show(APPstar.this,"没有获取到token");
                }else {
                    startPresenter.startApp(UrlIPconfig.GONGSIIP,token);
                }
                login.setVisibility(View.VISIBLE);
                zhuce.setVisibility(View.VISIBLE);

            }
        },time);



        
    }

    public void logins(View v){
        Intent intent=new Intent(APPstar.this,Login.class);
        startActivity(intent);
        APPstar.this.finish();
    }
    public void register(View v){
        Intent intent=new Intent(APPstar.this,Register.class);
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

           Intent intent=new Intent(APPstar.this,MainActivity.class);
           startActivity(intent);
           APPstar.this.finish();
           ToastUtils.show(APPstar.this,startokenbean.getMessage());
       }else {
           ToastUtils.show(APPstar.this,startokenbean.getMessage());
       }
    }
}

