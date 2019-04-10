package com.winhex.wys.wys.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.winhex.wys.wys.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArticlActivity extends AppCompatActivity  implements OnTitleBarListener {

    WebView Mwebview;
    String url;
    TitleBar MtitleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articl);
        find();
        Mwebview.loadUrl(url);
        MtitleBar.setOnTitleBarListener(this);
    }

    private void find() {
        Mwebview=findViewById(R.id.urls);
        MtitleBar=findViewById(R.id.artilistBar);
        Intent intent=getIntent();
        url=intent.getStringExtra("url");
        MtitleBar.setTitle(intent.getStringExtra("Articlename"));
    }

    @Override
    public void onLeftClick(View v) {
        Intent intent=new Intent(ArticlActivity.this,MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onTitleClick(View v) {

    }

    @Override
    public void onRightClick(View v) {

    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(ArticlActivity.this,MainActivity.class);
        startActivity(intent);
        ArticlActivity.this.finish();
    }
}
