package com.winhex.wys.wys.datails;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.winhex.wys.wys.R;
import com.winhex.wys.wys.datails.Adapter.PhotoPagerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Datails extends AppCompatActivity {
    private ViewPager viewPager;
    private TextView number_tx;
    private ArrayList<String> urlList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datails_activity);
        initparm();
        initview();
    }

    private void initparm() {
        Intent intent=getIntent();
        String  urls=  intent.getStringExtra("urls");
        String[] arr = urls.split(",");
        urlList=new ArrayList<>();
        Collections.addAll(urlList,arr);
    }

    private void initview() {

        viewPager =findViewById(R.id.DataIls_vp);
        number_tx =findViewById(R.id.DataIls_tx);
        PhotoPagerAdapter photoPagerAdapter=new PhotoPagerAdapter(getSupportFragmentManager(),urlList);
        viewPager.setAdapter(photoPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                number_tx.setText(String.valueOf(i+1)+"/"+urlList.size());
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        }


}
