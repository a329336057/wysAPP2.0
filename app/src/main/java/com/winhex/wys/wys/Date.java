package com.winhex.wys.wys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public  class  Date {


    public static List<String> height(List<String> list){
        for (int i = 0; i <70 ; i++) {
           int a=i+120;
            list.add(String.valueOf(a)+"cm");
        }
        return list;
    }
    public static List<String> sex(){
        List<String> sex=new ArrayList<>();
        sex.add("男");
        sex.add("女");
        return sex;
    }
    public static List<String> classfiy(){
        List<String> sex=new ArrayList<>();
        sex.add("美食");//美食
        sex.add("心情");//心情
        sex.add("旅游");//旅游
        sex.add("自拍");//自拍
        sex.add("记录");//记录
        sex.add("分享");//分享
        sex.add("打广告");//打广告
        return sex;
    }

}
