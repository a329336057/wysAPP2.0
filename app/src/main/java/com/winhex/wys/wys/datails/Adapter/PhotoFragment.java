package com.winhex.wys.wys.datails.Adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.ToastUtils;

import java.util.List;


public class PhotoFragment extends Fragment {
    String url;
    PhotoView photoView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_photo,container,false);
        findid(v);
        return  v;
    }

    @Override
    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        url=getArguments().getString("url");
    }
    public  static PhotoFragment newinstantate(String url){
        PhotoFragment photoFragment=new PhotoFragment();
        Bundle args=new Bundle();
        args.putString("url",url);
        photoFragment.setArguments(args);
        return photoFragment;
    }
    private void findid(View v) {
        photoView=v.findViewById(R.id.Photo_view);
        photoView.setScaleType(ImageView.ScaleType.CENTER);
        photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                ToastUtils.show(getContext(),"关闭该activity");
            }
        });
        Glide.with(getContext())
                .load(url)
                .into(photoView);

    }


}
