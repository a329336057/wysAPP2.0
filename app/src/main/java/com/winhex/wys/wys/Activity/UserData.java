package com.winhex.wys.wys.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.winhex.wys.wys.Presenter.Register.IregisterPresenter;
import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.ToastUtils;
import com.winhex.wys.wys.View.Iregisterview;
import com.winhex.wys.wys.bean.Registerbean;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserData extends AppCompatActivity implements  Iregisterview {
    ImageView mHeightSelector,mSexSelctor,mBirthdaySelector;
    TextView mHeight, mSex,mBirthday,mPhone,mNickname;
    Button perfect_data;
    IregisterPresenter iregisterPresenter;
    List<String> height=new ArrayList<>();//身高
    List<String> sex=new ArrayList<>();//性别
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        findid();
        selector();
        perfect_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mHeight.equals(null) &&mBirthday.equals(null)&&mNickname.equals(null)&&mPhone.equals(null)&&mSex.equals(null)){
                    ToastUtils.show(UserData.this,"请填写完整信息");
                }else {

                }
            }
        });
    }

    private void selector() {
        mHeightSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                height.clear();
                height= com.winhex.wys.wys.Date.height(height);
                //条件选择器
                OptionsPickerView pvOptions = new OptionsPickerBuilder(UserData.this, new OnOptionsSelectListener() {

                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {

                        String tx = height.get(options1);
                        mHeight.setText(tx);
                    }
                }).build();
                pvOptions.setPicker(height);
                pvOptions.setSelectOptions(20);
                pvOptions.show();

            }
        });
        mSexSelctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex.clear();
                sex= com.winhex.wys.wys.Date.sex();
                OptionsPickerView pickerView=new OptionsPickerBuilder(UserData.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        String tx=sex.get(options1);
                        mSex.setText(tx);
                    }
                }).build();
                pickerView.setPicker(sex);
                pickerView.show();
            }
        });
        mBirthdaySelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(UserData.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mBirthday.setText(getTime(date));
                    }
                }).build();
                pvTime.setTitleText("你的生日");
                pvTime.show();
            }
        });
    }

    /**
     * 获取时间
     * @param date
     * @return
     */
    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return format.format(date);
    }
    private void findid() {
        perfect_data=findViewById(R.id.perfect_data);
        mHeightSelector=findViewById(R.id.height_selector);
        mSexSelctor=findViewById(R.id.sex_selector);
        mSex=findViewById(R.id.sex);
        mHeight=findViewById(R.id.height);
        mBirthday=findViewById(R.id.Birthday);
        mBirthdaySelector=findViewById(R.id.Birthday_selector);
        mPhone=findViewById(R.id.phone);
        mNickname=findViewById(R.id.nickname);

    }

    @Override
    public void getDataFailed(Throwable e) {

    }

    @Override
    public void getDataSuccess(Registerbean registerbean) {

    }
}
