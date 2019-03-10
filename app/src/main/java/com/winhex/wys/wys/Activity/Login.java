package com.winhex.wys.wys.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.winhex.wys.wys.Iview;
import com.winhex.wys.wys.Model.ModelLogin;
import com.winhex.wys.wys.Presenter.PresenterImpl;
import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.SharedPreferencesUtil;
import com.winhex.wys.wys.Utils.ToastUtils;
import com.winhex.wys.wys.bean.Loginbean;

public class Login extends AppCompatActivity implements Iview {

    EditText username,password;
    PresenterImpl presenter;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter=new PresenterImpl(this);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.logins);
        
        
    }
    void userlogin(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals(null) && password.getText().toString().equals(null)){
                    presenter.login("http://106.12.199.218:8080/wys/",username.getText().toString(),password.getText().toString());
                }else {
                    ToastUtils.show(Login.this,"账号或密码不能为空");

                }
            }
        });
    }
    
    

    @Override
    public void getDataFailed(Throwable e) {
  
        ToastUtils.show(Login.this,e.getMessage());
    }

    @Override
    public void getDataSuccess(Loginbean loginbean) {
        ToastUtils.show(Login.this,loginbean.getMessage());
        SharedPreferencesUtil.putData("token",loginbean.getToken());
    }

   
}
