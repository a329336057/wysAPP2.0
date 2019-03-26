package com.winhex.wys.wys.Activity.Release;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
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
import com.winhex.wys.wys.Utils.IsGpsWork;
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
    TextView location_tv;
    List<String> listfile;
    List<String> list=new ArrayList<>();

    //声明AMapLocationClient类对象
    AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;


     GeocodeSearch geocodeSearch;

    Selectphotos selectphotos=new Selectphotos();
    UploadPresenterImpl uploadPresenter;
    //上传照片

    @SuppressLint("HandlerLeak")
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
        getCurrentLocationLatLng();
        findid();

        ToastUtils.show(Publish.this,upload_context.getText().toString());
        select_phton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectphotos.multiSelect(Publish.this,REQUEST_LIST_CODE);
            }
        });
        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(upload_context.getText().toString())){
                    if (!TextUtils.isEmpty(typetextview.getText().toString())&&typetextview.getText().toString()!="未选择"){
                        if(listfile.size()>0){
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

    /**
     *  初始化定位并设置定位回调监听
     */
    private void getCurrentLocationLatLng(){
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

 /* //设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景） 设置了场景就不用配置定位模式等
    option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
    if(null != locationClient){
        locationClient.setLocationOption(option);
        //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
        locationClient.stopLocation();
        locationClient.startLocation();
    }*/
        // 同时使用网络定位和GPS定位,优先返回最高精度的定位结果,以及对应的地址描述信息
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //只会使用网络定位
        /* mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);*/
        //只使用GPS进行定位
        /*mLocationOption.setLocationMode(AMapLocationMode.Device_Sensors);*/
        // 设置为单次定位 默认为false
        /*mLocationOption.setOnceLocation(true);*/
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。默认连续定位 切最低时间间隔为1000ms
        mLocationOption.setInterval(3500);
        //设置是否返回地址信息（默认返回地址信息）
        /*mLocationOption.setNeedAddress(true);*/
        //关闭缓存机制 默认开启 ，在高精度模式和低功耗模式下进行的网络定位结果均会生成本地缓存,不区分单次定位还是连续定位。GPS定位结果不会被缓存。
        /*mLocationOption.setLocationCacheEnable(false);*/
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
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
        titleBar.setOnTitleBarListener(this);
        listfile=new ArrayList<>();
        location_tv=findViewById(R.id.local);

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
        Intent intent=new Intent(Publish.this,MainActivity.class);
        startActivity(intent);
        Publish.this.finish();
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






    /**
     * 定位回调监听器
     */
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (!IsGpsWork.isGpsEnabled(getApplicationContext())) {
              ToastUtils.show(Publish.this,"GPS未开启");
            } else {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        double currentLat = amapLocation.getLatitude();//获取纬度
                        double currentLon = amapLocation.getLongitude();//获取经度
                        if (amapLocation != null) {
                            if (amapLocation.getErrorCode() == 0) {
                                location_tv.setText(amapLocation.getCity()+amapLocation.getDistrict()+amapLocation.getStreet()+amapLocation.getStreetNum()+amapLocation.getFloor()  );
                            //可在其中解析amapLocation获取相应内容。
                            }else {
                                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                                Log.e("AmapError","location Error, ErrCode:"
                                        + amapLocation.getErrorCode() + ", errInfo:"
                                        + amapLocation.getErrorInfo());
                            }
                        }
                        /*currentLatLng = new LatLng(currentLat, currentLon);*/   //latlng形式的

                        Log.i("currentLocation", "currentLat : " + currentLat + " currentLon : " + currentLon);
                        amapLocation.getAccuracy();//获取精度信息
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        }
    };



}
