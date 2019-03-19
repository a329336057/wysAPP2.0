package com.winhex.wys.wys.Activity.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.winhex.wys.wys.Activity.Adapter.ClassifyAdapter;
import com.winhex.wys.wys.Activity.Release.Publish;
import com.winhex.wys.wys.Activity.Selectphotos;
import com.winhex.wys.wys.R;


import java.util.ArrayList;
import java.util.List;



import static android.app.Activity.RESULT_OK;


public class ClassifyFragment extends Fragment implements OnTitleBarListener {
    TitleBar mtitleBar;
    List<String> mtitel;
    List<Integer> mimages;
    RecyclerView mrecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_classify, container, false);
        findid(v);
        recyclersetting();
        return  v;
    }

    /**
     * 列表设置
     */
    private void recyclersetting() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerView.setLayoutManager(gridLayoutManager);
        mrecyclerView.setAdapter(new ClassifyAdapter(getContext(),mtitel,mimages));


     }

    private void findid(View v) {
        mtitleBar=v.findViewById(R.id.classify_titlebar);
        mtitel=new ArrayList<>();
        mimages=new ArrayList<>();
        mrecyclerView=v.findViewById(R.id.classify_RecyclerView);
        mtitleBar.setOnTitleBarListener(this);
            mtitel.add("美食");
            mtitel.add("心情");
            mtitel.add("旅游");
            mtitel.add("自拍");
            mtitel.add("记录");
            mtitel.add("分享");
            mtitel.add("打广告");

            mimages.add(R.drawable.classify_1);
            mimages.add(R.drawable.classify_2);
            mimages.add(R.drawable.classify_3);
            mimages.add(R.drawable.classify_4);
            mimages.add(R.drawable.classify_5);
            mimages.add(R.drawable.classify_6);
            mimages.add(R.drawable.classify_7);


    }

    @Override
    public void onLeftClick(View v) {

    }

    @Override
    public void onTitleClick(View v) {

    }

    @Override
    public void onRightClick(View v) {
        Intent intent=new Intent(getActivity(),Publish.class);
        startActivity(intent);
        getActivity().finish();
    }
}
