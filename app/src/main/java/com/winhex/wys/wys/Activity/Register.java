package com.winhex.wys.wys.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.winhex.wys.wys.Presenter.Register.RegisterPresenterImpl;
import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.ToastUtils;
import com.winhex.wys.wys.Utils.UrlIPconfig;
import com.winhex.wys.wys.View.Iregisterview;
import com.winhex.wys.wys.bean.Registerbean;

public class Register extends AppCompatActivity implements OnTitleBarListener,Iregisterview {
    EditText mUserEdite,mPassWordEditeOne,mPassWordEditeTow;
    RadioButton  mRegisterService;
    Button mRegisterButton;
    TitleBar mTitlebar;
    RegisterPresenterImpl registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FindId();
        registerPresenter=new RegisterPresenterImpl(this);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=mUserEdite.getText().toString();
                String mpasswordone=mPassWordEditeOne.getText().toString();
                String mpasswordtow=mPassWordEditeTow.getText().toString();
                if(username.length()>1 && mpasswordone.length()>1 && mpasswordtow.length()>1){
                    if(mpasswordone.equals(mpasswordtow)){
                    registerPresenter.register(UrlIPconfig.GONGSIIP,username,mpasswordone);
                    }else {
                        ToastUtils.show(Register.this,"密码错误 请重新输入");
                    }
                }else {
                    ToastUtils.show(Register.this,"注册或密码不能为空");
                }


            }
        });

    }

    void FindId(){
        mTitlebar=findViewById(R.id.Registerbar);
        mUserEdite=findViewById(R.id.Register_User);
        mPassWordEditeOne=findViewById(R.id.Register_PasswordOne);
        mPassWordEditeTow=findViewById(R.id.Register_PasswordTow);
        mRegisterService=findViewById(R.id.user_service);
        mRegisterButton=findViewById(R.id.register_button);
        mTitlebar.setOnTitleBarListener(this);
    }

    /**
     * 注册数据返回
     * @param e
     */
    @Override
    public void getDataFailed(Throwable e) {
        e.getMessage();
    }

    @Override
    public void getDataSuccess(Registerbean registerbean) {
            if(registerbean.getCode()==200){
                ToastUtils.show(Register.this,"注册成功");
                Intent intent=new Intent(Register.this,Login.class);
                startActivity(intent);
                Register.this.finish();
            }else {
                ToastUtils.show(Register.this,registerbean.getMessage());
            }
    }
    /**
     * 标题栏和返回功能
     * @param v
     */
    @Override
    public void onLeftClick(View v) {
        Intent intent=new Intent(Register.this,Login.class);
        startActivity(intent);
        Register.this.finish();
    }

    @Override
    public void onTitleClick(View v) {

    }

    @Override
    public void onRightClick(View v) {

    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Register.this,Login.class);
        startActivity(intent);
        Register.this.finish();
    }


}
