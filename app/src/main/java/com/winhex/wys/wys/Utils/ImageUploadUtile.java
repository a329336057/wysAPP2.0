package com.winhex.wys.wys.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


public class ImageUploadUtile {

    public static MultipartBody filesToMultipartBody(List<File> files) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            builder.addFormDataPart("file", file.getName(), requestBody);
        }

        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }
    public  static   List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files, Context context) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
            for (File filesd : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), filesd);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", filesd.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

    public static  List<File> Path_strTOFile(List<String> path){
        List<File> fileList=new ArrayList<>();
        for (int i = 0; i <path.size() ; i++) {
            File file=new File(path.get(i));
            fileList.add(file);
        }
        return  fileList;
    }
}
