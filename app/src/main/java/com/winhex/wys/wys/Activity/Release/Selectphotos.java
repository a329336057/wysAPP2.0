package com.winhex.wys.wys.Activity.Release;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.winhex.wys.wys.R;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISCameraConfig;
import com.yuyh.library.imgsel.config.ISListConfig;

import org.w3c.dom.Text;

@SuppressLint("sad")
public class Selectphotos {
    /**
     * 实例化图片选择器
     */
    public   Selectphotos(){
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
    }

    /**
     * 照相机
     * @param activity
     * @param REQUEST_CAMERA_CODE
     */
    public void camera(Activity activity,int REQUEST_CAMERA_CODE){
        ISCameraConfig config = new ISCameraConfig.Builder()
                .needCrop(true) // 裁剪
                .cropSize(1, 1, 1000, 1000)
                .build();


        ISNav.getInstance().toCameraActivity(activity, config, REQUEST_CAMERA_CODE);
    }

    /**
     * 选择图片
     * @param activity
     * @param t
     * @param REQUEST_LIST_CODE
     */
    public void selectimage(Activity activity, TextView t,int REQUEST_LIST_CODE){
        t.setText("");
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

        ISNav.getInstance().toListActivity(activity, config, REQUEST_LIST_CODE);
    }

    /**
     * 记录图片选择
     * @param activity
     * @param t
     * @param REQUEST_LIST_CODE
     */
    public  void multiSelect(Activity activity,int REQUEST_LIST_CODE){
      
        ISListConfig config = new ISListConfig.Builder()
                .multiSelect(true)
                // 是否记住上次选中记录
                .rememberSelected(false)
                // 使用沉浸式状态栏
                .statusBarColor(Color.parseColor("#3F51B5")).build();

        ISNav.getInstance().toListActivity(activity, config, REQUEST_LIST_CODE);
    }
}
