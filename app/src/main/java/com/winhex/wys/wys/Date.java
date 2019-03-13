package com.winhex.wys.wys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Date {


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

}
