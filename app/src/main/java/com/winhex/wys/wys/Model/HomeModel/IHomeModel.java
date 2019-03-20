package com.winhex.wys.wys.Model.HomeModel;

import com.winhex.wys.wys.bean.Homebean;

import java.util.List;

import okhttp3.MultipartBody;
import rx.Observable;

public interface IHomeModel {
    Observable<Homebean> getHomeData(String url, String token);
}
