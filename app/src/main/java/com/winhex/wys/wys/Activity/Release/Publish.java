package com.winhex.wys.wys.Activity.Release;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.winhex.wys.wys.Activity.Selectphotos;
import com.winhex.wys.wys.Presenter.Upload.IUploadPresenter;
import com.winhex.wys.wys.Presenter.Upload.UploadPresenterImpl;
import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.ImageUploadUtile;
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

public class Publish extends AppCompatActivity  implements IUploadView {
    private static final int REQUEST_LIST_CODE = 0;
    private static final int REQUEST_CAMERA_CODE = 1;
    Button mSelect_images;
    ImageView mimages;
    TextView mImage_url;
    Button  msgin;
    Button mMultiselect;
    List<String> pathList;
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
                List<File> fileList=ImageUploadUtile.Path_strTOFile(pathList);
                List<MultipartBody.Part> partList=ImageUploadUtile.filesToMultipartBodyParts(fileList);
                uploadPresenter.geinformation(UrlIPconfig.GONGSIIP,partList);
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
        pathList=new ArrayList<>();
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

                Glide.with(Publish.this).load(path).into(mimages);
            }
        }
        //单图上传
//        else if (requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK && data != null) {
//            String path = data.getStringExtra("result");
//            mImage_url.append(path + "\n");
//            Glide.with(Publish.this).load(path).into(mimages);
//        }
    }

    @Override
    public void getDataFailed(Throwable e) {

    }

    @Override
    public void getDateSuccess(Uploadbean uploadbean) {

    }
    public static void uploadImage(DisposableObserver<ResponseBody> subscriber, String type, List<String> fileUrl) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//表单类型;
//        //多张图片
//        for (int i = 0; i < fileUrl.size(); i++) {
//            File file = new File(fileUrl.get(i));//filePath 图片地址
//            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//            builder.addFormDataPart("file", file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名
//        }
//        File file = new File(fileUrl.get(0));//filePath 图片地址
//        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        builder.addFormDataPart("type", type);//imgfile 后台接收图片流的参数名
//        builder.addFormDataPart("signUrl", "/common/uploadImage");//imgfile 后台接收图片流的参数名
//        builder.addFormDataPart("version", "1.0");
//        builder.addFormDataPart("file", file.getName(), imageBody);//imgfile 后台接收图片流的参数名
//        List<MultipartBody.Part> parts = builder.build().parts();
//        Observable<ResponseBody> observable =  HttpMethods.getInstance().getHttpApi().uploadImage(parts);
//        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }






}
