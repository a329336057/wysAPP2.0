package com.winhex.wys.wys.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hjq.bar.TitleBar;
import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.SharedPreferencesUtil;

public class MainActivity extends AppCompatActivity {

    Button mSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSignOut=findViewById(R.id.SignOut);
        /**
         * 退出登陆
         */
        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtil.getInstance(MainActivity.this,"tokens");
                SharedPreferencesUtil.Remove("token");
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }
}
