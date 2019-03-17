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
import com.winhex.wys.wys.Activity.Selectphotos;
import com.winhex.wys.wys.R;




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
    Selectphotos selectphotos=new Selectphotos();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        View v=inflater.inflate(R.layout.fragment_release, container, false);
        findid(v);
        msgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectphotos.camera(getActivity(),REQUEST_CAMERA_CODE);
            }
        });
        mSelect_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectphotos.selectimage(getActivity(),mImage_url,REQUEST_LIST_CODE);
            }
        });
        mMultiselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectphotos.multiSelect(getActivity(),mImage_url,REQUEST_LIST_CODE);
            }
        });
        return v;


    }

 



    private void findid(View v) {
        mSelect_images=v.findViewById(R.id.select_images);
        mImage_url=v.findViewById(R.id.image_url);
        msgin=v.findViewById(R.id.sgin);
        mimages=v.findViewById(R.id.images);
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
            Glide.with(getActivity()).load(path).into(mimages);
        }
    }   


}
