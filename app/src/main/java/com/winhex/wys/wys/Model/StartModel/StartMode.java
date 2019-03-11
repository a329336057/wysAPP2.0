package com.winhex.wys.wys.Model.StartModel;

import com.winhex.wys.wys.Model.LoginModel.ModelLogin;
import com.winhex.wys.wys.RetrofitFactory;
import com.winhex.wys.wys.bean.Startokenbean;

import rx.Observable;

public class StartMode implements IstartModel {
    private static StartMode instance;

    public synchronized static StartMode getInstance() {

        if (null == instance) {
            synchronized (StartMode.class) {
                instance = new StartMode();
            }
        }

        return instance;

    }
    @Override
    public Observable<Startokenbean> getValidationToken(String baseUrl, String token) {
        return RetrofitFactory.getInstance().getCustomHaierAPi(baseUrl).ValidationToken(token);
    }
}
