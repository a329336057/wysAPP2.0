package com.winhex.wys.wys.Activity.Release;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.winhex.wys.wys.Activity.MainActivity;
import com.winhex.wys.wys.Activity.Selectphotos;
import com.winhex.wys.wys.LoginSystemActivity.APPstar;
import com.winhex.wys.wys.LoginSystemActivity.Login;
import com.winhex.wys.wys.LoginSystemActivity.Register;
import com.winhex.wys.wys.Presenter.Upload.IUploadPresenter;
import com.winhex.wys.wys.Presenter.Upload.UploadPresenterImpl;
import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.ImageUploadUtile;
import com.winhex.wys.wys.Utils.SharedPreferencesUtil;
import com.winhex.wys.wys.Utils.ToastUtils;
import com.winhex.wys.wys.Utils.UrlIPconfig;
import com.winhex.wys.wys.View.IUploadView;
import com.winhex.wys.wys.bean.Uploadbean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class Publish extends AppCompatActivity  implements IUploadView,OnTitleBarListener {
    private static final int REQUEST_LIST_CODE = 0;
    private static final int REQUEST_CAMERA_CODE = 1;



    TitleBar titleBar;
    Button mSelect_images;
    ImageView mimages;
    TextView mImage_url;
    Button  msgin;
    Button mMultiselect;
    List<String> pathList;
    List<String> listfile;


    Selectphotos selectphotos=new Selectphotos();
    UploadPresenterImpl uploadPresenter;
    //上传照片
    Button mUploadImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_publish);
        findid();
        msgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectphotos.camera(Publish.this,REQUEST_CAMERA_CODE);
            }
        });
        mSelect_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectphotos.selectimage(Publish.this,mImage_url,REQUEST_LIST_CODE);
            }
        });
        mMultiselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectphotos.multiSelect(Publish.this,mImage_url,REQUEST_LIST_CODE);
            }
        });
        mUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<File> fileList=ImageUploadUtile.Path_strTOFile(listfile);
                List<MultipartBody.Part> partList=ImageUploadUtile.filesToMultipartBodyParts(fileList,Publish.this);
                SharedPreferencesUtil.getInstance(Publish.this,"tokens");
                String token=(String) SharedPreferencesUtil.getData("token","获取失败");
                uploadPresenter.getUpload(UrlIPconfig.GONGSIIP,partList,token,"1","你好啊小姐");
            }
        });
    }

    private void findid() {

        mSelect_images=findViewById(R.id.select_images);
        mImage_url=findViewById(R.id.image_url);
        msgin=findViewById(R.id.sgin);
        mimages=findViewById(R.id.images);
        mMultiselect=findViewById(R.id.Multiselect);
        mUploadImage=findViewById(R.id.publish_image);
        uploadPresenter=new UploadPresenterImpl(this);
        titleBar=findViewById(R.id.publish_tobar);
        pathList=new ArrayList<>();
        listfile=new ArrayList<>();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LIST_CODE && resultCode == RESULT_OK && data != null) {
            pathList.clear();
            pathList = data.getStringArrayListExtra("result");
            // 测试Fresco
            // draweeView.setImageURI(Uri.parse("file://"+pathList.get(0)));
            for (String path : pathList) {
                mImage_url.setText(path + "\n");
                Luban.with(this)
                        .load(path)                                  // 传人要压缩的图片列表
                        .ignoreBy(100)                              // 忽略不压缩图片的大小
                        // 设置压缩后文件存储位置
                        .setCompressListener(new OnCompressListener() { //设置回调
                            @Override
                            public void onStart() {
                                ToastUtils.show(Publish.this,"开始压缩了");
                            }
                            @Override
                            public void onSuccess(File file) {
                                listfile.add(file.getPath());
                            }
                            @Override
                            public void onError(Throwable e) {
                               ToastUtils.show(Publish.this,e.getMessage());
                            }
                        }).launch();    //启动压缩
                Glide.with(Publish.this).load(path).into(mimages);
            }
        }
        //单图上传
    else if (requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK && data != null) {
         String path = data.getStringExtra("result");
         mImage_url.append(path + "\n");
         Glide.with(Publish.this).load(path).into(mimages);
      }
    }

    @Override
    public void getDataFailed(Throwable e) {
        ToastUtils.show(Publish.this,e.getMessage());
    }

    @Override
    public void getDateSuccess(Uploadbean uploadbean) {

        ToastUtils.show(Publish.this,uploadbean.getMsg());
    }


    @Override
    public void onLeftClick(View v) {
        Intent intent=new Intent(Publish.this,MainActivity.class);
        startActivity(intent);
        Publish.this.finish();
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Publish.this,MainActivity.class);
        startActivity(intent);
        Publish.this.finish();
    }
    @Override
    public void onTitleClick(View v) {

    }

    @Override
    public void onRightClick(View v) {

    }
}
