package com.winhex.wys.wys.LoginSystemActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.winhex.wys.wys.Activity.MainActivity;
import com.winhex.wys.wys.Utils.SingleClick;
import com.winhex.wys.wys.Utils.UrlIPconfig;
import com.winhex.wys.wys.View.Iloginview;
import com.winhex.wys.wys.Presenter.LoginSystemPresenter.Login.LoginImpl;
import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.SharedPreferencesUtil;
import com.winhex.wys.wys.Utils.ToastUtils;
import com.winhex.wys.wys.bean.Loginbean;

public class Login extends AppCompatActivity implements Iloginview ,OnTitleBarListener{

    EditText username,password;
    LoginImpl presenter;
    Button login,register;
    TitleBar mTitleBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter=new LoginImpl(this);
        SharedPreferencesUtil.getInstance(Login.this,"tokens");
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.logins);
        mTitleBar=findViewById(R.id.loginbar);
        register=findViewById(R.id.register_loginbutton);
        userlogin();
        register();
        mTitleBar.setOnTitleBarListener(this);
        
    }
        void  register(){
        register.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
                Login.this.finish();
            }
        });
    }
    void userlogin(){
        login.setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(username.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())){
                    presenter.login(UrlIPconfig.GONGSIIP,username.getText().toString(),password.getText().toString());
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
        //判断接口中的token是否存在
        if(loginbean.getToken()!=null){
            //将接口中的token写入
            SharedPreferencesUtil.putData("token",loginbean.getToken());
          
          if(loginbean.getIsPerfectinformation().equals("0")){
            
              
              Intent intent=new Intent(Login.this,UserData.class);
              intent.putExtra("user",username.getText().toString());
              startActivity(intent);
              Login.this.finish();
              
          }else {
              Intent intent=new Intent(Login.this,MainActivity.class);
              startActivity(intent);
              Login.this.finish();
              ToastUtils.show(Login.this,"欢迎您 伙伴");
          }
        }else {
            ToastUtils.show(Login.this,loginbean.getMessage());
        }

    }


    @Override
    public void onBackPressed() {
        ToastUtils.show(Login.this,"返回个JB");
    }


    @Override
    public void onLeftClick(View v) {

    }

    @Override
    public void onTitleClick(View v) {

    }

    @Override
    public void onRightClick(View v) {

    }


}
