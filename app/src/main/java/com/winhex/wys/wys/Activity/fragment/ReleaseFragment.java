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
import com.winhex.wys.wys.R;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISCameraConfig;
import com.yuyh.library.imgsel.config.ISListConfig;


import java.io.File;
import java.util.List;



import static android.app.Activity.RESULT_OK;


public class ReleaseFragment extends Fragment {
    private static final int REQUEST_LIST_CODE = 0;
    private static final int REQUEST_CAMERA_CODE = 1;
    Button mSelect_images;
    ImageView mimages;
    TextView mImage_url;
    Button  msgin;
    Button mMultiselect;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_release, container, false);
        findid(v);
// 自定义图片加载器
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });

        mSelect_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ISCameraConfig config = new ISCameraConfig.Builder()
                        .needCrop(true) // 裁剪
                        .cropSize(1, 1, 200, 200)
                        .build();


                ISNav.getInstance().toCameraActivity(getActivity(), config, REQUEST_CAMERA_CODE);
            }
        });
        msgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImage_url.setText("");
                ISListConfig config = new ISListConfig.Builder()
                        // 是否多选
                        .multiSelect(false)
                        .btnText("Confirm")
                        // 确定按钮背景色
                        //.btnBgColor(Color.parseColor(""))
                        // 确定按钮文字颜色
                        .btnTextColor(Color.WHITE)
                        // 使用沉浸式状态栏
                        .statusBarColor(Color.parseColor("#3F51B5"))
                        // 返回图标ResId
                        .backResId(R.mipmap.back)
                        .title("Images")
                        .titleColor(Color.WHITE)
                        .titleBgColor(Color.parseColor("#3F51B5"))
                        .allImagesText("All Images")
                        .needCrop(true)
                        .cropSize(1, 1, 200, 200)
                        // 第一个是否显示相机
                        .needCamera(true)
                        // 最大选择图片数量
                        .maxNum(9)
                        .build();

                ISNav.getInstance().toListActivity(getActivity(), config, REQUEST_LIST_CODE);
            }
        });

        mMultiselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImage_url.setText("");
                ISListConfig config = new ISListConfig.Builder()
                        .multiSelect(true)
                        // 是否记住上次选中记录
                        .rememberSelected(false)
                        // 使用沉浸式状态栏
                        .statusBarColor(Color.parseColor("#3F51B5")).build();

                ISNav.getInstance().toListActivity(getActivity(), config, REQUEST_LIST_CODE);
            }
        });
        return v;


    }





    private void findid(View v) {
        mSelect_images=v.findViewById(R.id.select_images);
        mImage_url=v.findViewById(R.id.image_url);
        msgin=v.findViewById(R.id.sgin);
        mMultiselect=v.findViewById(R.id.Multiselect);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LIST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra("result");

            // 测试Fresco
            // draweeView.setImageURI(Uri.parse("file://"+pathList.get(0)));
            for (String path : pathList) {
                mImage_url.append(path + "\n");

                Glide.with(getActivity()).load(path).into(mimages);
            }
        } else if (requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK && data != null) {
            String path = data.getStringExtra("result");
            mImage_url.append(path + "\n");

        }
    }


}
