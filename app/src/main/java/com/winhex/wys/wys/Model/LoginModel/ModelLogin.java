package com.winhex.wys.wys.Model.LoginModel;

import com.winhex.wys.wys.RetrofitFactory;
import com.winhex.wys.wys.bean.Loginbean;
import com.winhex.wys.wys.bean.Startokenbean;

import rx.Observable;

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

    /**
     * 调用登录接口
     * @param baseUrl
     * @param username
     * @param password
     * @return
     */
    @Override
    public Observable<Loginbean> getlogins(String baseUrl, String username, String password) {
        return RetrofitFactory.getInstance().getCustomHaierAPi(baseUrl).getlogin(username,password);
    }

}


