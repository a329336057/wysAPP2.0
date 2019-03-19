package com.winhex.wys.wys.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.winhex.wys.wys.Activity.Adapter.MainActivityViewPagerAdapter;
import com.winhex.wys.wys.Activity.fragment.HomeFragent;
import com.winhex.wys.wys.Activity.fragment.MyFragment;
import com.winhex.wys.wys.Activity.fragment.ClassifyFragment;

import com.winhex.wys.wys.R;


public class MainActivity extends AppCompatActivity  {

    long mExitTime =0;
    ViewPager mviewPager;
    FragmentManager fragmentManager;
    BottomNavigationView mbottomNavigationMenuView;
    MainActivityViewPagerAdapter mmainActivityViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settinglayout();


    }

    /**
     * 设置布局
     */
    private void settinglayout() {
        mviewPager=findViewById(R.id.main_viewpager);
        mbottomNavigationMenuView=findViewById(R.id.navigation);
//        设置Adapter
        mmainActivityViewPagerAdapter= new MainActivityViewPagerAdapter(getSupportFragmentManager());
        mmainActivityViewPagerAdapter.addFragment(new HomeFragent());
        mmainActivityViewPagerAdapter.addFragment(new ClassifyFragment());
        mmainActivityViewPagerAdapter.addFragment(new MyFragment());
        mviewPager.setAdapter(mmainActivityViewPagerAdapter);

//        fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.add()


        mbottomNavigationMenuView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//               获取到菜单项的Id
                int itemId = item.getItemId();
//                当第一项被选择时BlankFragmentOne显示，以此类推
                switch (itemId) {
                    case R.id.homeblack:
                        mviewPager.setCurrentItem(0);
                        break;
                    case R.id.Release:
                        mviewPager.setCurrentItem(1);
                        break;
                    case R.id.My:
                        mviewPager.setCurrentItem(2);
                        break;


                }
                // true 会显示这个Item被选中的效果 false 则不会
                return true;
            }
        });

        /**
         * 滑动监听
         */
        mviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                当 ViewPager 滑动后设置BottomNavigationView 选中相应选项
                mbottomNavigationMenuView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mmainActivityViewPagerAdapter.getItem(1).onActivityResult(requestCode, resultCode, data);
    }



}

