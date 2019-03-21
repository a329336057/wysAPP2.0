package com.winhex.wys.wys.Activity.Release;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.winhex.wys.wys.Activity.Adapter.ClassifyAdapter;
import com.winhex.wys.wys.Activity.Adapter.LateralAdapter;
import com.winhex.wys.wys.Activity.MainActivity;
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

import okhttp3.MultipartBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class Publish extends AppCompatActivity  implements IUploadView,OnTitleBarListener {
    private static final int REQUEST_LIST_CODE = 0;
    private static final int REQUEST_CAMERA_CODE = 1;


   EditText upload_context;
    TitleBar titleBar;
    LinearLayout linearLayout;
    Button upload_button,select_phton;
    RecyclerView recyclerView;
    TextView typetextview;
    PhtonAdapter phtonAdapter;

    List<String> listfile;
    List<String> list=new ArrayList<>();


    Selectphotos selectphotos=new Selectphotos();
    UploadPresenterImpl uploadPresenter;
    //上传照片

    private Handler handler=new Handler(){
        @Override
        public  void  handleMessage(Message message){
            if(message.what==1){
                phtonAdapter=new PhtonAdapter(Publish.this,listfile);
                recyclerView.setAdapter(phtonAdapter);
                recyclerViewsetting();
                phtonAdapter.notifyDataSetChanged();
                ToastUtils.show(Publish.this,"刷新成功数量："+listfile.size());
                listfile=list;
                list.clear();
            }
        }
    };
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        findid();
       
        select_phton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectphotos.multiSelect(Publish.this,REQUEST_LIST_CODE);
            }
        });
        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(upload_context.getText().toString()!=null &&upload_context.getText().toString()!=""){
                    if (typetextview.getText()!="请选择"){
                        if(listfile!=null){
                            List<File> fileList=ImageUploadUtile.Path_strTOFile(listfile);
                            List<MultipartBody.Part> partList=ImageUploadUtile.filesToMultipartBodyParts(fileList,Publish.this);
                            SharedPreferencesUtil.getInstance(Publish.this,"tokens");
                            String token=(String) SharedPreferencesUtil.getData("token","获取失败");
                            uploadPresenter.getUpload(UrlIPconfig.GONGSIIP,partList,token,typetextview.getText().toString(),upload_context.getText().toString());
                        }else {
                            ToastUtils.show(Publish.this,"没有图片哦 分享必须要有图片才行！");
                        }
                    }else {
                        ToastUtils.show(Publish.this,"请选择发布的类型呢");
                    }
                }else {
                    ToastUtils.show(Publish.this,"请填写内容或上传图片选择类型哦");
                }
               
                
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<String> classfiy= com.winhex.wys.wys.Date.classfiy();
                OptionsPickerView pickerView=new OptionsPickerBuilder(Publish.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        String tx=classfiy.get(options1);
                        typetextview.setText(tx);
                    }
                }).build();
                pickerView.setPicker(classfiy);
                pickerView.show();
            }
        });
    }

    private void findid() {
        linearLayout=findViewById(R.id.upload_type);
        select_phton=findViewById(R.id.select_phton);
        upload_button=findViewById(R.id.upload_button);
        typetextview=findViewById(R.id.typetextview);
        upload_context=findViewById(R.id.upload_context);
        recyclerView=findViewById(R.id.upload_phton);
        uploadPresenter=new UploadPresenterImpl(this);
        titleBar=findViewById(R.id.publish_tobar);
        listfile=new ArrayList<>();
    
 
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LIST_CODE && resultCode == RESULT_OK && data != null) {
            listfile.clear();
            listfile = data.getStringArrayListExtra("result");
            // 测试Fresco
            // draweeView.setImageURI(Uri.parse("file://"+pathList.get(0)));
            for (String path : listfile) {
                
                Luban.with(this)
                        .load(path)                                  // 传人要压缩的图片列表
                        .ignoreBy(100)                              // 忽略不压缩图片的大小
                        // 设置压缩后文件存储位置
                        .setCompressListener(new OnCompressListener() { //设置回调
                            @Override
                            public void onStart() {
                              
                            }
                            @Override
                            public void onSuccess(File file) {
                                list.add(file.getPath());
                            }
                            @Override
                            public void onError(Throwable e) {
                            
                            }
                        }).launch();    //启动压缩
              
            }
            
            Message message=new Message();
            message.what=1;
            handler.sendMessage(message);
            
        }
//        //单图上传
//    else if (requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK && data != null) {
//         String path = data.getStringExtra("result");
//         mImage_url.append(path + "\n");
//         Glide.with(Publish.this).load(path).into(mimages);
//      }
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



    /**
     * 设置样式
     */
    private void recyclerViewsetting() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(Publish.this,3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
     

    }

}
