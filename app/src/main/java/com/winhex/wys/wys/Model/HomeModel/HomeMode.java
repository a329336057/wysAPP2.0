package com.winhex.wys.wys.Model.HomeModel;

import com.winhex.wys.wys.RetrofitFactory;
import com.winhex.wys.wys.bean.Homebean;

import java.util.List;

import okhttp3.MultipartBody;
import rx.Observable;

public class HomeMode  implements IHomeModel{
        private static HomeMode homeMode;

    public synchronized static HomeMode getInstance() {

        if (null == homeMode) {
            synchronized (HomeMode.class) {
                homeMode = new HomeMode();
            }
        }

        return homeMode;
    }


    @Override
    public Observable<Homebean> getHomeData(String url, String token) {
        return RetrofitFactory.getInstance().getCustomHaierAPi(url).getHomedata(token);
    }
}
