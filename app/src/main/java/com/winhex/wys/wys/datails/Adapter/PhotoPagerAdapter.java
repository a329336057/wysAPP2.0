package com.winhex.wys.wys.datails.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PhotoPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> list;

    public PhotoPagerAdapter(FragmentManager fm,ArrayList<String> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int i) {
        return PhotoFragment.newinstantate(list.get(i));
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
