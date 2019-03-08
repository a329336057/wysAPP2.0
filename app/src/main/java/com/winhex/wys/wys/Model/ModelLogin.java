package com.winhex.wys.wys.Model;

import com.winhex.wys.wys.RetrofitFactory;
import com.winhex.wys.wys.bean.Loginbean;

import rx.Observable;

import static okhttp3.internal.Internal.instance;

public class ModelLogin implements ILoginModel {
    private static ModelLogin instance;

    public synchronized static ModelLogin getInstance() {

        if (null == instance) {
            synchronized (ModelLogin.class) {
                instance = new ModelLogin();
            }
        }

        return instance;

    }

    @Override
    public Observable<Loginbean> getlogins(String baseUrl, String username, String password) {
        return RetrofitFactory.getInstance().getCustomHaierAPi(baseUrl).getlogin(username,password);
    }
}


