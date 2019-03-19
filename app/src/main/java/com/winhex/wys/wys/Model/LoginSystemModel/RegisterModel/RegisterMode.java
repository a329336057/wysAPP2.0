package com.winhex.wys.wys.Model.LoginSystemModel.RegisterModel;

import com.winhex.wys.wys.RetrofitFactory;
import com.winhex.wys.wys.bean.Registerbean;

import rx.Observable;

public class RegisterMode implements  IRegisterModel{
    private static RegisterMode instance;

    public synchronized static RegisterMode getInstance() {

        if (null == instance) {
            synchronized (RegisterMode.class) {
                instance = new RegisterMode();
            }
        }

        return instance;

    }

    @Override
    public Observable<Registerbean> getRegister(String baseUrl, String username, String password) {
        return RetrofitFactory.getInstance().getCustomHaierAPi(baseUrl).getregister(username,password);
    }


}
